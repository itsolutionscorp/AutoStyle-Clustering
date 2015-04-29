class Luhn
  attr_reader :number

  def initialize(number)
    @number = number.to_s
  end

  def addends
    array = []
    number.chars.reverse.each_with_index do |digit, index|
      unless index.even?
        array << resolve_number(digit)
      else
        array << digit.to_i
      end
    end
    array.reverse
  end

  def resolve_number digit
    number = digit.to_i * 2
    number > 10 ? number - 9 : number
  end

  def checksum
    addends.inject(:+)
  end

  def valid?
    checksum.to_s[-1] == "0"
  end

  def self.create number
    new_number = number.to_s + "0"
    new_number = new_number.to_i
    loop do
      return new_number if new(new_number).valid?
      new_number += 1
    end
  end
end
