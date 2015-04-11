class Matrix
  attr_reader :rows

  def initialize(string)
    @rows = string.lines.map{|line| line.split.map(&:to_i)}
  end
  
  def columns
    @rows.transpose
  end

  def saddle_points
    @rows.each_with_index.each_with_object([]) do |(row, i), points|
      row.each_with_index {|el, j| points << [i,j] if saddle_point?(i,j)}
    end
  end

  private

  def saddle_point?(x,y)
    element = rows[x][y]
    element == rows[x].max && element == columns[y].min
  end
end
