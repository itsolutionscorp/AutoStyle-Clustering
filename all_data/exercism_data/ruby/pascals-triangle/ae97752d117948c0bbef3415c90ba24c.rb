class Triangle
  attr_reader :rows

  def initialize(size)
    @rows = generate_rows(size)
  end

  private

  def generate_rows(size)
    (0..size-1).inject([]) do |result, num|
      result << row(num)
    end
  end

  def row(num)
    (0..num-1).each_with_object([1]) do |el, line|
      line.push(line[el] * (num-el) / (el + 1))
    end
  end

end
