class ValueError < StandardError; end

class Board
  MINE = '*'.freeze
  BLANK = ' '.freeze
  VALID_CHARS = /\A[\+\-\| \*]{3,}\z/
  VALID_TOP_AND_BOTTOM_BORDERS = /\A\+\-+\+\z/
  VALID_SIDE_BORDERS = /\A\|.+\|\z/

  def self.transform(input)
    new(input).transform
  end

  def initialize(input)
    @input = input

    validate_input
  end

  def transform
    input.map.with_index do |row, row_index|
      row.each_char.map.with_index do |space, col_index|
        number = 0
        if space == BLANK
          number += number_of_adjacent_spaces_with_mines(row_index, col_index)
        end
        number > 0 ? number.to_s : space
      end.join
    end
  end

  private

  attr_reader :input

  def validate_input
    raise ValueError if irregular_lengths? || faulty_border? || invalid_char?
  end

  def irregular_lengths?
    input.map(&:length).uniq.size != 1
  end

  def faulty_border?
    [input.first, input.last].any? { |row| row !~ VALID_TOP_AND_BOTTOM_BORDERS } ||
      input[1..-2].any? { |row| row !~ VALID_SIDE_BORDERS }
  end

  def invalid_char?
    input.any? { |row| row !~ VALID_CHARS }
  end

  def number_of_adjacent_spaces_with_mines(row_index, col_index)
    adjacent_spaces(row_index, col_index).reduce(0) do |sum, adj_space|
      adj_space == MINE ? sum + 1 : sum
    end
  end

  def adjacent_spaces(row_index, col_index)
    [
      input[row_index - 1][col_index - 1],
      input[row_index - 1][col_index],
      input[row_index - 1][col_index + 1],
      input[row_index][col_index - 1],
      input[row_index][col_index + 1],
      input[row_index + 1][col_index - 1],
      input[row_index + 1][col_index],
      input[row_index + 1][col_index + 1]
    ]
  end
end
