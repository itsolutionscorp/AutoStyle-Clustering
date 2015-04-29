class Matrix
  attr_reader :rows, :columns

  def initialize(matrix_string)
    @rows = []
    @columns = []
    parse_string(matrix_string)
  end

  private

  def parse_string(matrix_string)
    matrix_string.lines.each do |line|
      @rows << line.split.map(&:to_i)
    end
    @rows.each do |row|
      row.each_with_index do |col, index|
        @columns[index] ||= []
        @columns[index] << col
      end
    end
  end
end
