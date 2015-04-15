class Luhn
  def initialize(number)
    @number = number
  end

  def digits
    @number.to_s.each_char.map { |digit| digit.to_i }
  end

  def addends
    digits.reverse.map.with_index do |digit, index| 
      number = digit * ((index % 2) + 1)
      if number > 9
        number = number.to_s.split(//).reduce(0) {|acc, digit| acc += digit.to_i }
      end
      number
    end.reverse
  end

  def checksum
    addends.reduce(:+)
  end

  def valid?
    checksum % 10 == 0
  end

  def self.create(partial)
    number = partial * 10
    checksum = Luhn.new(number).checksum % 10
    if checksum == 0
      number
    else
      number + 10 - checksum
    end
  end

end
