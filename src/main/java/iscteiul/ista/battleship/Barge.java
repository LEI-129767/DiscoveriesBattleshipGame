/**
 *
 */
package iscteiul.ista.battleship;

/**
 * Representa a Barca, o navio mais pequeno da frota (equivalente ao
 * submarino da Batalha Naval tradicional), ocupando uma única posição
 * no tabuleiro.
 */
public class Barge extends Ship {
    private static final Integer SIZE = 1;
    private static final String NAME = "Barca";

    /**
     * Cria uma nova Barca numa posição do tabuleiro. Por ocupar apenas
     * uma casa, a orientação não altera a sua disposição.
     *
     * @param bearing orientação da barca
     * @param pos     posição superior esquerda da barca
     */
    public Barge(Compass bearing, IPosition pos) {
        super(Barge.NAME, bearing, pos);
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
    }

    /**
     * Devolve o número de casas ocupadas pela Barca no tabuleiro.
     *
     * @return o tamanho da barca, sempre igual a 1
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }

}
