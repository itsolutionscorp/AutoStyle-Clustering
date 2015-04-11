class Trinary

  def initialize(string)
   string.match(/[^0-9]/) ? @string = "0" : @string = string
  end

  def to_decimal
    bin_digits = @string.split('').map(&:to_i)
    multipliers = (0..bin_digits.length-1).to_a.reverse
    
    bin_digits.zip(multipliers).map {|a,b| a * (3 ** b)}.inject(:+)
  end

end
