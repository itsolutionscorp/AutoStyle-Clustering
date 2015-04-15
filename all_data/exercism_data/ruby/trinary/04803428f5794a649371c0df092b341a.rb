class Trinary
  def initialize(number)
    @number = number
  end

  def to_decimal
    return 0 unless valid?
    digits.each_with_index.map { |digit, index| digit * (3 ** index) }.inject(:+)
  end

private

  def valid?
    @number =~ /^[012]+$/
  end

  def digits
    @number.chars.reverse.map { |digit| digit.to_i }
  end

end
