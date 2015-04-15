class Matrix
  def initialize string
    @matrix = string.split("\n").map { |row| row.split(' ').map(&:to_i)}
  end

  def rows
    return Array.new @matrix
  end

  def columns
    return @matrix.transpose
  end
end
