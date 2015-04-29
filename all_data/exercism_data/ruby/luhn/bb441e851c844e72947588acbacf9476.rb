class Luhn

  def self.create(number)
    new("#{number}0").create
  end

  def initialize(number)
    @number = number.to_s
  end

  def addends
    @number.reverse.chars.each_with_index.map do |char, index|
      lunh_transform(char.to_i, index)
    end.reverse
  end

  def checksum
    addends.reduce(:+)
  end

  def valid?
    checksum % 10 == 0
  end

  def create
    @number[-1] = check_digit.to_s unless valid?
    @number.to_i
  end

  private
  def lunh_transform(digit, index)
    index % 2 == 0 ? digit : doubled_digit_transform(digit)
  end

  private
  def doubled_digit_transform(digit)
    (digit = digit * 2) > 10 ? digit - 9 : digit
  end

  private
  def check_digit
    valid? ? 0 : 10 - (checksum % 10)
  end

end
