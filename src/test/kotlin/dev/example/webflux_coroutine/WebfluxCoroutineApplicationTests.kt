package dev.example.webflux_coroutine

import dev.example.webflux_coroutine.model.Article
import dev.example.webflux_coroutine.repository.ArticleRepository
import io.kotest.core.spec.style.StringSpec
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class WebfluxCoroutineApplicationTests(
	@Autowired private val repository: ArticleRepository,
): StringSpec({
	"context load"/*.config(false)*/ {
		val preCount = repository.count()
		repository.save(Article(title = "title1", body = "body"))
		val currCount = repository.count()
		Assertions.assertEquals(preCount + 1, currCount)
	}
})