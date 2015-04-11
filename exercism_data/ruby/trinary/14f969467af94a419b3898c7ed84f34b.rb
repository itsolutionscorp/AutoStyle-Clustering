# class to compute decimal value from trinary
class Trinary
  def initialize(n)
    @n = n
  end

  def to_decimal
    @n.chars
      .reverse
      .each_with_index
      .reduce([]) { |result, (val, idx)| result << val.to_i * 3**idx }
      .reduce(&:+)
  end
end
