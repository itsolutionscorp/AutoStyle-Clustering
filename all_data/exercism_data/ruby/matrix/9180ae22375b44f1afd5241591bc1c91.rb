class Matrix
  attr_reader :rows

  def initialize(data)
    @rows = parse_string_matrix(data)
  end

  def columns
    columns = []
    rows.length.times do |i|
      columns << find_column(rows, i)
    end
    columns
  end

  private

  def parse_string_matrix(string_matrix)
    string_rows = find_string_rows(string_matrix)
    convert_data_to_integers(string_rows)
  end

  def find_string_rows(string_matrix)
    string_matrix.split("\n").map{ |row| row.split(" ") }
  end

  def convert_data_to_integers(rows)
    rows.map { |row| row.map { |datum| datum.to_i } }
  end

  def find_column(rows, i)
    rows.map { |row| row[i] }
  end

end
