package dev.example.webflux_coroutine.service

import dev.example.webflux_coroutine.repository.ArticleRepository
import io.kotest.core.spec.style.FunSpec
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ArticleServiceTest (
    @Autowired private val service: ArticleService,
    @Autowired private val repository: ArticleRepository,
): StringSpec({

    "create and get" {
        val prevCnt = repository.count()
        val created = service.create(ReqCreate("title1"))
        val get = service.get(created.id)

        get.id shouldBe created.id
        get.title shouldBe created.title
        get.authorId shouldBe created.authorId
        get.createdAt shouldNotBe null
        get.updatedAt shouldNotBe null

    }

})