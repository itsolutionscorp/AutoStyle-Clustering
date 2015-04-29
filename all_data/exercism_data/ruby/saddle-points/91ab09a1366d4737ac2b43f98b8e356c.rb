class Matrix

  attr_reader :string

  def initialize string
    @string = string
  end

  def saddle_points
    points.select { |point| saddle_point? *point }
  end

  def rows
    @rows ||= matrix
  end

  def columns
    @columns ||= matrix.transpose
  end

private

  def saddle_point? x, y
    row     = rows[x]
    column  = columns[y]
    element = rows[x][y]

    row.max == element and column.min == element  
  end

  def points
    (0...matrix.size).to_a.product ( ( 0...matrix.transpose.size ).to_a )
  end

  def matrix
    string.split( "\n" ).map { |row| row.split.map &:to_i }
  end

end
