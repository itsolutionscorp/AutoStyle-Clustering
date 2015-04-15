class Triangle

  attr_reader :max

  def initialize(input)
    @max = input
  end

  def rows
    rows = []
    max.times do |i|
      rows << build_next_row(i, rows.last)
    end
    rows
  end

  private

  def build_next_row(i, last_row)
    row = Array.new(i+1)

    row.each_with_index do |item, j|
      if j == 0 || j == row.length - 1
        row[j] = 1
      else
        row[j] = (last_row[j]  + last_row[j-1])
      end
    end

  end

end
