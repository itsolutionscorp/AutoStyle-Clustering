class Matrix

  attr_accessor :rows, :columns

  def initialize(matrix)
    @rows = matrix.split("\n").map do |row|
      row.split(" ").map {|item| item.to_i }
    end
    @columns = @rows.transpose
  end

  def saddle_points
    saddle_points = []
    @rows.each_with_index do |row, i|
      row.each_with_index do |col, j|
        if is_saddle_point?(i,j)
          saddle_points << [i,j] 
        end
      end
    end
    saddle_points
  end

  def is_saddle_point?(x,y)
    cell = rows[x][y]
    cell == rows[x].max && cell == columns[y].min ||
    cell == rows[x].min && cell == columns[y].max
  end

end
