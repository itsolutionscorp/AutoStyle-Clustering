class Triangle
  attr_reader :rows, :height

  def initialize(height)
    @height = height
    @rows ||= generate_rows
  end

  def generate_rows
    height.times.reduce([]) do |ary, i|
      ary << generate_new_row(ary.last)
    end
  end

  def generate_new_row(ary)
    return [1] if ary.nil?  # return 1 if first row
    ary.each_cons(2).reduce([1]) do |row, pair|
      row << pair.reduce(:+)
    end.push(1)
  end
end
