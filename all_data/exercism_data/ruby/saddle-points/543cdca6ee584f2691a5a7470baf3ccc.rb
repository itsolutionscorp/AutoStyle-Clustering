class Matrix
  def initialize(m)
    @matrix = m.split("\n").map { |x| x.scan(/\d+/).map(&:to_i) }
  end

  def rows
    @matrix
  end

  def columns
    @matrix.transpose
  end

  def saddle_points
    points = []
    rows.each_with_index do |row, i|
      row.each_with_index do |item, j|
        if item == row.max and item == columns[j].min
          points << [i, j]
        end
      end
    end
    points
  end
end
