class Board
  BORDER_CHARS = [ '+', '|' ]
  VALID_CHARS = [' ', '*', '-' ] + BORDER_CHARS

  def self.transform(inp)
    length = inp.first.size

    inp.map.with_index do |line, i|
      cells = line.chars

      raise ValueError unless
        length == cells.length &&
          BORDER_CHARS.include?(cells[0]) &&
          BORDER_CHARS.include?(cells[-1])

      cells.map.with_index do |char, j|
        raise ValueError unless VALID_CHARS.include? char

        char == ' ' and count_mines(inp, i, j) or char
      end.join
    end
  end

  private

  NEIGHBOURS = [*-1..1].product([*-1..1]) - [[0, 0]]

  def self.count_mines(inp, i, j)
    count =
      NEIGHBOURS
        .map { |x, y| inp[i+x][j+y] }
        .count { |e| e == '*' }

    count == 0 and ' ' or count
  end
end

class ValueError < Exception; end
