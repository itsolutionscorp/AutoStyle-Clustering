class Hamming

  def self.compute(a, b)
    Hamming.new(a,b).difference_between_strands
  end

  def initialize(a, b)
    @a = a
    @b = b
  end

  def difference_between_strands
    @differences = 0
    count_differences_in_strings
    @differences
  end

private

  def count_differences_in_strings
    min_length = [@a.length, @b.length].min
    min_length.times do |i|
      @differences += difference(@a[i], @b[i])
    end
  end

  def difference(x, y)
    x == y ? 0 : 1
  end
end
