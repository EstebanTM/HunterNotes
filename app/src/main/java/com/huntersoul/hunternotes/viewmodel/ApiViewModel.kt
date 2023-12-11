package com.huntersoul.hunternotes.viewmodel

//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.huntersoul.hunternotes.models.Character
//import com.huntersoul.hunternotes.repository.CharacterRepository
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//class ApiViewModel @Inject constructor(
//    private val characterRepository: CharacterRepository
//):ViewModel() {
//    private val _state = MutableStateFlow(emptyList<Character>())
//    val state:StateFlow<List<Character>>
//        get() = _state
//
//    init {
//        viewModelScope.launch {
//            val characters = characterRepository.getCharacters()
//            _state.value = characters
//        }
//    }
//}