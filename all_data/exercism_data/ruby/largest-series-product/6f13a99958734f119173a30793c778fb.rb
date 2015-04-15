class Series
  def initialize(input)
    @input = input
  end

  def digits
    @digits ||= @input.chars.map(&:to_i)
  end

  def slices(size)
    raise ArgumentError if size > digits.size
    digits.each_cons(size).to_a
  end

  def largest_product(size)
    return 1 if size.zero?
    slices(size).map do |ary|
      ary.reduce(&:*)
    end.max
  end

end
