class Matrix
  attr_reader :string_matrix, :rows, :columns

  def initialize(string_matrix)
    @string_matrix = string_matrix
    @rows = matrix_rows
    @columns = matrix_columns
  end

  def saddle_points
    find_min_or_max(rows, :max).select do |point|
      find_min_or_max(columns, :min).include?(point)
    end
  end

  private

  def parsed_elements
    string_matrix.split("\n").map do |row|
      row.split(" ")
    end
  end

  def matrix_rows
    parsed_elements.map do |row|
      row.map { |datum| datum.to_i }
    end
  end

  def matrix_columns
    columns = []
    rows.length.times do |i|
      columns << rows.map { |row| row[i] }
    end
    columns
  end

  def find_min_or_max(collection, bound)
    indexes = outer_indexes_for(collection, bound)
    possiblities_for(indexes, bound)
  end

  def outer_indexes_for(collection, bound)
    collection.map do |e|
      outer = e.send(bound)

      results = []
      e.each_with_index do |element, i|
        results << i if element == outer
      end
      results
    end
  end

  def possiblities_for(indexes, bound)
    possible_points = []
    indexes.each_with_index do |coords, i|
      possible_points += possibilities_for(coords, i, bound)
    end
    possible_points
  end

  def possibilities_for(coords, i, bound)
    if bound == :min
      coords.map { |coord| [coord, i] }
    else
      coords.map { |coord| [i, coord] }
    end
  end

end
