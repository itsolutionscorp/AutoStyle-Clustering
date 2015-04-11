class ValueError < Exception; end

class Board
  OFFSETS = [[-1, -1], [-1, 0], [-1, 1], [0, -1], [0, 1], [1, -1], [1, 0], [1, 1]]

  attr_reader :rows

  def initialize(rows)
    raise ValueError unless input_valid?(rows)

    @rows = rows
    @width = rows.first.length - 2
    @height = rows.length - 2
  end

  def get(row, column)
    @rows[row][column]
  end

  def set(row, column, value)
    @rows[row][column] = value
  end

  def count_mines
    (1..@height).each do |row|
      (1..@width).each do |column|
        value = get(row, column)
        increment_surrounding(row, column) if value == "*"
      end
    end
  end

  def self.transform(rows)
    board = self.new(rows.map(&:chars))
    board.count_mines
    board.rows.map(&:join)
  end

  private

  def increment_surrounding(row, column)
    surrounding_coordinates = get_surrounding_coordinates(row, column)
    surrounding_coordinates.each do |x, y|
      value = get(x, y)
      if value == "*"
        next
      elsif value == " "
        value = 1
      else
        value += 1
      end
      set(x, y, value)
    end
  end

  def get_surrounding_coordinates(row, column)
    OFFSETS.map { |row_offset, column_offset|
      [row + row_offset, column + column_offset]
    }.select { |coordinates| in_bounds?(*coordinates) }
  end

  def in_bounds?(row, column)
    row >= 1 && row <= @height &&
      column >= 1 && column <= @width
  end

  def input_valid?(rows)
    all_rows_same_length?(rows) && borders_fine?(rows) && valid_characters?(rows)
  end

  def all_rows_same_length?(rows)
    rows.map(&:length).uniq.length == 1
  end

  def borders_fine?(rows)
    horizontal_borders = [rows.first, rows.last]
    columns = rows.transpose
    vertical_borders = [columns.first, columns.last]
    horizontal_borders.all? { |border| border_fine?(border, "-") } &&
      vertical_borders.all? { |border| border_fine?(border, "|") }
  end

  def border_fine?(border, border_character)
    border.first == "+" && border.last == "+" &&
      (border[1...-1].all? { |value| value == border_character})
  end

  def valid_characters?(rows)
    rows.all? { |row| row.all? { |value| value =~ /[+\|* -]/ } }
  end
end
