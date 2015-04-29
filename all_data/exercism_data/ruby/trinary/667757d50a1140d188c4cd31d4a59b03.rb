class Trinary
  attr_reader :string
  def initialize(string)
    @string = string
  end

  def to_decimal
    digits = string.reverse.split('').map(&:to_i)
    ans = []
    digits.each_with_index{|val, index| ans << val * 3**(index)}
    ans.inject(:+)
  end
end
