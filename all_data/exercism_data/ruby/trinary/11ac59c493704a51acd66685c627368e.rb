# class to compute decimal value from trinary
class Trinary
  BASE = 3

  def initialize(n)
    @n = n
  end

  def to_decimal
    @n.chars
      .reverse
      .each_with_index
      .reduce([]) { |result, (val, idx)| result << val.to_i * BASE**idx }
      .reduce(&:+)
  end
end
