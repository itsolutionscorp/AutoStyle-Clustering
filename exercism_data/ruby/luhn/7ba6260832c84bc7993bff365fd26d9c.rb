class Luhn
  def initialize(number)
    @number = number.to_s.reverse
  end

  def addends
    return @luhn_cake if @luhn_cake
    @luhn_cake = []
    @number.each_char.with_index do | number, index|
      if index % 2 != 0
        num = number.to_i * 2
        num -= 9 if num > 10
        @luhn_cake.unshift(num)
      else
        @luhn_cake.unshift(number.to_i)
      end
    end
    @luhn_cake
  end

  def checksum
    addends.inject(0) { |sum, num| sum + num}
  end

  def valid?
    checksum % 10 == 0
  end

  def self.create(number)
    test_number = number * 10
    luhn = self.new(test_number)
    if luhn.valid?
      test_number
    else
      (1..9).each do |dig|
        number_string = number.to_s + dig.to_s
        luhn = self.new(number_string)
        if luhn.valid?
          return number_string.to_i
        end
      end
    end
  end
end
