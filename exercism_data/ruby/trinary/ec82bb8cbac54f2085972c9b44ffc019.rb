class Trinary
  def initialize(n)
    @digits = convert(n)
  end

  def to_decimal
    @digits.each_with_index.inject(0) do |sum, (digit, idx)|
      sum += digit*3**idx
    end
  end

  private

  def convert(n)
    if n.to_i.to_s != n
      return [0]
    else
      a = n.chars.inject([]) { |a,v| a << v.to_i }
      a.reverse
    end
  end
end
