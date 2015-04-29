class Triangle
  def initialize(height)
    @height = height
  end

  def rows
    (1..@height).map { |row| get_row(row) }
  end

  private

  def get_row(n)
    row = Array.new(n)
    row[0] = 1
    i = 1
    multiplicand = n - 1
    divisor = 1
    while i < n
      previous_value = row[i - 1]
      row[i] = previous_value * multiplicand / divisor
      multiplicand -= 1
      divisor += 1
      i += 1
    end
    row
  end
end
