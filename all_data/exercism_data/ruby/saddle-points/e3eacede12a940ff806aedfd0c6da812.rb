class Matrix

  attr_reader :rows, :columns

  def initialize(string)
    @rows    = parse(string)
    @columns = @rows.transpose
  end

  def saddle_points
    coordinates.select do |coord|
      is_saddle_point? coord
    end
  end

  private
  def coordinates
    coordinates = []
    (0...(rows.count)).each do |row| 
      (0...(columns.count)).map do |col|
        coordinates << [row,col]
      end
    end
    coordinates
  end

  def is_saddle_point?(coord)
    value = rows[coord[0]][coord[1]]
    rows[coord[0]].all? { |n| value >= n } && 
    columns[coord[1]].all? { |n| value <= n }
  end

  def parse(string)
    string.lines.map { |row| row.split.map(&:to_i) }
  end

end
