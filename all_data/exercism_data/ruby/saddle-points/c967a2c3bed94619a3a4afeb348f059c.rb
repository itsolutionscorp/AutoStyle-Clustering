class Matrix
  def initialize string_matrix
    @matrix = parse string_matrix
  end
  
  def rows
    @matrix.dup
  end
  
  def columns
    @matrix.transpose
  end
  
  def saddle_points
    each_cell_position_with_object([]) do |row,col,list|
      list << [row,col] if saddle_point? row,col
    end
  end
  
  private
  def each_cell_position_with_object obj
    rows.each_index{|row| columns.each_index{|col| yield(row,col,obj) }}
    return obj
  end
  
  def saddle_point? row,col
    point = @matrix[row][col]
    rows[row].max == point && columns[col].min == point
  end
  
  def parse string
    string.split("\n").map{|row| row.split(" ").map(&:to_i) }
  end
end
