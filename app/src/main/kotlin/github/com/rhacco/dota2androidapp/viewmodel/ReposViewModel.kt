package github.com.rhacco.dota2androidapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MediatorLiveData
import github.com.rhacco.dota2androidapp.entities.HeroEntity
import github.com.rhacco.dota2androidapp.sources.repos.HeroesRepository
import io.reactivex.disposables.CompositeDisposable

open class ReposViewModel(application: Application?) : AndroidViewModel(application) {
    // TODO: maybe organize the states of this into a base mViewModel?
    // This stateful observation seems pretty useful for any given API call we're going to make.
    val isLoadingLiveData = MediatorLiveData<Boolean>()
    val throwableLiveData = MediatorLiveData<Throwable>()

    val heroListSearchQueryLiveData = MediatorLiveData<Pair<String, List<HeroEntity>>>()

    private val disposables = CompositeDisposable()

    override fun onCleared() = disposables.clear()

    fun getHero(hero: String) {
        isLoadingLiveData.value = true
        disposables.add(HeroesRepository
                .getHeroByLocalName(hero)
                .subscribe(
                        { result ->
                            isLoadingLiveData.value = false
                            heroListSearchQueryLiveData.value = Pair(hero, result)
                        },
                        { error ->
                            isLoadingLiveData.value = false
                            throwableLiveData.value = error
                        })
        )
    }
}
