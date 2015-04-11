require 'pry'

class PhoneNumber
  def initialize(number)
    @number = number.gsub(/[^0-9]/,"")
  end

  def number
    if @number[0] == "1" && @number.length == 10
      @number
    elsif @number[0] == "1" && @number.length == 11
      @number[1..11]
    else
      "0000000000"
    end
  end

  def area_code
    self.number[0..2]
  end

  def to_s
    "(#{self.area_code}) #{self.number[3..5]}-#{self.number[6..10]}"
  end
end
