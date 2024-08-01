package dev.example.webflux_coroutine.service

import dev.example.webflux_coroutine.model.Article
import dev.example.webflux_coroutine.repository.ArticleRepository
import dev.example.webflux_reactor.exception.NoArticleException
import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Service
class ArticleService (
    private val repository: ArticleRepository,
){
    // @Transactional
    suspend fun create(request: ReqCreate): Article {
        return repository.save(request.toArticle())
    }

    suspend fun get(id: Long): Article {
        return repository.findById(id) ?: throw NoArticleException("id: $id")
    }

    suspend fun getAll(title: String? = null): Flow<Article> {
        return if(title.isNullOrBlank()) {
            repository.findAll()
        } else {
            repository.findAllByTitleContains(title)
        }
    }

    suspend fun update(id: Long, request: ReqUpdate): Article {
        val article = repository.findById(id) ?: throw NoArticleException("id: $id")
        return repository.save(article.apply {
            request.title?.let { title = it }
            request.body?.let { body = it }
            request.authorId?.let { authorId = it }
        })
    }

    suspend fun delete(id: Long) {
        return repository.deleteById(id)
    }

}

data class ReqCreate(
    var title: String,
    var body: String? = null,
    var authorId: Long? = null,
) {

    fun toArticle(): Article {
        return Article(
            title = this.title,
            body = this.body,
            authorId = this.authorId,
        )
    }
}


data class ReqUpdate(
    val title: String? = null,
    var body: String? = null,
    var authorId: Long? = null,
)