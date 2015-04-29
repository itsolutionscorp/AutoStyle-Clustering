class Matrix
  def initialize matrix_string
    @rows = matrix_string.lines.map { |l| parse_row(l) }
    @columns = compute_columns
  end
  
  attr_reader :rows, :columns
  
  private
  
  def parse_row(str)
    str.scan(/\d+/).map &:to_i
  end
  
  def compute_columns
    count = @rows.first.size
    
    (0...count).each_with_object([]) do |i, columns|
      columns[i] = @rows.map { |row| row[i] }
    end
  end
end
