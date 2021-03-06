package github.com.rhacco.dota2androidapp.sources.repos

import github.com.rhacco.dota2androidapp.entities.HeroEntity
import io.reactivex.Single

interface HeroesDataSource {
    fun getRepositories(): Single<List<HeroEntity>>
    fun getHeroByLocalName(hero: String): Single<List<HeroEntity>>
    fun saveRepositories(list: List<HeroEntity>) = Unit
}