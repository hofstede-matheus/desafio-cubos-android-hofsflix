package com.matheushofstede.hofsflix.Presenters

import com.matheushofstede.hofsflix.Interfaces.MoviePresenterInterface
import com.matheushofstede.hofsflix.Interfaces.MovieViewInterface


class MoviePresenter(view: MovieViewInterface): MoviePresenterInterface {
    // eu criei essa presenter pq meu raciocínio era fazer outra requisição pra pegar os detalhes dos filmes usando essa rota GET /movie/{movie_id}
    // Mas eu vi que as informações que eu precisava já estavam na outra requisição que fiz, então não achei necessário
    // agora a rota GET /movie/{movie_id} tem absurdamente mais detalhes, como os generos do filme em string (não somente os ids), production_countries, status e etc
    // Enfim, deixei a interface e a presenter pra caso em um futuro eu queria pegar uma informação que só tenha na rota /movie , ficar já estruturado aqui e nos padrões
    // há braços
    var view: MovieViewInterface = view
}