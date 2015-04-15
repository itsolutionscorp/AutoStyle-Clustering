class Matrix
  attr_reader :rows

  def initialize(matrix_string)
    @rows = matrix_string.split(/\r?\n/ ).map do |row| 
      row.split.map { |element| element.to_i }
    end
  end

  def columns
    rows.transpose
  end
end
