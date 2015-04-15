class Series
  attr_reader :digits

  def initialize(input)
    @digits = parse_digit_string(input)
  end

  def slices(count)
    return [[1]] if digits.empty?
    digits.each_cons(count).to_a
  end

  def largest_product(count)
    raise ArgumentError unless count <= digits.length
    slices(count).map{ |pair| pair.inject(&:*) }.max
  end

  private

  def parse_digit_string(digit_string)
    digit_string.chars.map{ |c| c.to_i }
  end
end
