class Matrix
  def initialize(string)
    @matrix = string.lines.map{|line| line.split.map(&:to_i)}
  end
  
  def rows
    @matrix
  end

  def columns
    @matrix.transpose
  end

  def saddle_points
    @matrix.each_with_index.each_with_object([]) do |(row, i), points|
      row.each_with_index {|el, j| points << [i,j] if saddle_point?(i,j)}
    end
  end

  def saddle_point?(x,y)
    element = rows[x][y]
    element == rows[x].max && element == columns[y].min
  end
end
