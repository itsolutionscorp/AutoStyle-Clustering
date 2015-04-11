require 'pry'

class PhoneNumber
  def initialize(number)
    @number = number
  end

  def number
    num = @number.split('')
    num = num.each_with_index { |value, index|  num[index] = Integer(value) rescue nil }
    num = num.select{ |a| a if Integer(a) rescue false }

    if num[0] == 1 && num.length == 10
      num.join
    elsif num[0] ==1 && num.length == 11
      num[1..11].join
    else
      "0000000000"
    end
  end

  def area_code
    num = self.number.split('')
    num[0..2].join
  end

  def to_s
    num = self.number.split('')
    "(#{self.area_code}) #{num[3..5].join}-#{num[6..10].join}"
  end

end
