class Luhn

  attr_reader :number

  def initialize(number)
    @number = number
  end

  def addends
    double_every_other_digit.map { |d| d.odd? && d>9 ? d-9 : d }
  end

  def checksum
    addends.reduce(:+)
  end

  def valid?
    actual_checksum == 0
  end

  # Multiply by 10 will ensure that you double the first number
  def self.create(number)
    new(number * 10).correct_checksum
  end

  # .take everything except the last number and shovel the correct checksum
  def correct_checksum
    digits.take(digits.length-1).<<(actual_checksum).join.to_i
  end

  private
  def reverse
    y = 0
    n = number
    while n > 0 do
      y = y*10
      y = y + (n%10)
      n = n/10    
    end
    y 
  end

  def digits
    number.to_s.chars.map(&:to_i)
  end

  def reversed_array
    digits.reverse
  end

  def double_every_other_digit
    reversed_array.map.with_index do |d,i| 
      i.odd? ? process_nine(d) : d 
    end.reverse
  end

  def process_nine(d)
    d*2 > 9 ? d*2 - 9 : d*2
  end

  def actual_checksum
    (10 - checksum) % 10
  end

end
