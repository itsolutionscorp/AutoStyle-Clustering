class Matrix
  def initialize(input)
    @input = input.split(/\n/)
  end

  def rows
    @input.map do |row|
      row.split(" ").map(&:to_i)
    end
  end

  def columns
    rows.transpose
  end

  def saddle_points
    coordinates = []
    rows.each_with_index do |row, j|
      max = row.max
      row.each_with_index do |number, i|
        min = columns[i].min
        if number == max && number == min
          coordinates << [j, i]
        end
      end
    end
    coordinates
  end
end
