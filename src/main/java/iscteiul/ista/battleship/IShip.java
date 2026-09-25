package iscteiul.ista.battleship;

import java.util.List;

/**
 * Representa um navio no jogo da Batalha Naval.
 * <p>
 * Define os atributos essenciais de um navio (como dimensão, orientação e posições ocupadas),
 * bem como o comportamento necessário para verificar sobreposições, proximidade com outros navios
 * e gestão dos tiros recebidos.
 * </p>
 *
 * @author Rui
 * @version 1.0
 */

public interface IShip {
    /**
     * Obtém a categoria ou tipo do navio (ex.: "Porta-Aviões", "Fragata", "Submarino").
     *
     * @return Nome da categoria do navio.
     */
    String getCategory();

    /**
     * Obtém o tamanho ou número de posições/quadriculas ocupadas pelo navio no tabuleiro.
     *
     * @return Tamanho do navio em número de posições.
     */
    Integer getSize();

    /**
     * Obtém a lista de todas as posições ocupadas pelo navio no tabuleiro.
     *
     * @return Lista contendo os objetos {@link IPosition} ocupados pelo navio.
     */
    List<IPosition> getPositions();

    /**
     * Obtém a posição de referência (âncora) do navio, geralmente correspondente à sua proa ou ponto inicial.
     *
     * @return Posição principal de referência {@link IPosition} do navio.
     */
    IPosition getPosition();

    /**
     * Obtém a orientação/direção para a qual o navio está voltado.
     *
     * @return A orientação {@link Compass} do navio (ex.: NORTH, SOUTH, EAST, WEST).
     */
    Compass getBearing();

    /**
     * Verifica se o navio ainda se encontra a flutuar (ou seja, se ainda possui pelo menos
     * uma posição que não tenha sido atingida por um tiro).
     *
     * @return {@code true} se o navio ainda estiver a flutuar; {@code false} caso tenha sido afundado.
     */
    boolean stillFloating();

    /**
     * Obtém a coordenada da linha mais ao topo (menor valor de linha) ocupada pelo navio.
     *
     * @return Índice da linha mais superior ocupada pelo navio.
     */
    int getTopMostPos();

    /**
     * Obtém a coordenada da linha mais ao fundo (maior valor de linha) ocupada pelo navio.
     *
     * @return Índice da linha mais inferior ocupada pelo navio.
     */
    int getBottomMostPos();

    /**
     * Obtém a coordenada da coluna mais à esquerda (menor valor de coluna) ocupada pelo navio.
     *
     * @return Índice da coluna mais à esquerda ocupada pelo navio.
     */
    int getLeftMostPos();

    /**
     * Obtém a coordenada da coluna mais à direita (maior valor de coluna) ocupada pelo navio.
     *
     * @return Índice da coluna mais à direita ocupada pelo navio.
     */
    int getRightMostPos();

    /**
     * Verifica se o navio ocupa uma determinada posição no tabuleiro.
     *
     * @param pos A posição {@link IPosition} a verificar.
     * @return {@code true} se o navio ocupar a posição especificada; {@code false} caso contrário.
     */
    boolean occupies(IPosition pos);

    /**
     * Verifica se este navio está demasiado próximo de outro navio (incluindo sobreposição ou posições adjacentes,
     * dependendo das regras do jogo).
     *
     * @param other O outro navio {@link IShip} com o qual se pretende verificar a proximidade.
     * @return {@code true} se os navios estiverem demasiado próximos/adjacentes; {@code false} caso contrário.
     */
    boolean tooCloseTo(IShip other);

    /**
     * Verifica se o navio está demasiado próximo de uma determinada posição específica.
     *
     * @param pos A posição {@link IPosition} a testar.
     * @return {@code true} se a posição for adjacente ou coincidente com o navio; {@code false} caso contrário.
     */
    boolean tooCloseTo(IPosition pos);

    /**
     * Regista o disparo efetuado numa determinada posição. Se a posição corresponder a uma das
     * posições ocupadas pelo navio, o estado dessa parte do navio deve ser atualizado para atingido.
     *
     * @param pos A posição {@link IPosition} onde é efetuado o disparo.
     */
    void shoot(IPosition pos);
}
