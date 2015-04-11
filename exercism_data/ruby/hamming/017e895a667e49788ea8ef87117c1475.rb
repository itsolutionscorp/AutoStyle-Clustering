class Hamming

  def self.compute(a, b)
    Hamming.new(a,b).difference_between_strands
  end

  def initialize(a, b)
    @a = a
    @b = b
  end

  def difference_between_strands
    return 0 if @a == @b
    compare_strands
  end

private

  def compare_strands
    @differences = 0
    count_differences_in_strings
    @differences
  end

  def count_differences_in_strings
    min_length = [@a.length, @b.length].min
    min_length.times do |i|
      @differences += difference_between_characters(@a[i], @b[i])
    end
  end

  def difference_between_characters(x, y)
    x == y ? 0 : 1
  end
end
