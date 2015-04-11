class Matrix
  attr_reader :rows, :columns

  def initialize(string)
    @string = string
    @rows = @string.lines.map {|line| line.split.map(&:to_i)}
    @columns = rows.transpose
  end

  def saddle_points
    coordinates.each_with_object([]) do |(x,y),saddle_points_array|
      coord = rows[x][y]
      if coord == rows[x].max && coord == columns[y].min
        saddle_points_array << [x,y]
      end
    end
  end

  def coordinates
    (0..rows.size-1).to_a.repeated_permutation(2).to_a
  end
end
