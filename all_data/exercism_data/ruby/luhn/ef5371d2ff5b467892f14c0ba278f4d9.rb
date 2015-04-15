class Luhn
  def initialize(num)
    @nums = num.to_s.split('').map {|i| i.to_i}
  end

  def addends
    @nums.reverse.map.with_index do |x, i|
      if i%2 != 0
        x *= 2
        x > 9 ? x -= 9 : x 
      else x end
    end.reverse
  end

  def checksum
    addends.inject(:+)
  end

  def valid?
    checksum % 10 == 0
  end

  def self.create(num)
    check_digit = (num.to_s + '0').to_i
    check = Luhn.new(check_digit)

    if check.checksum % 10 == 0
      check_digit
    else
      check_digit = 10 - (check.checksum % 10)
      (num.to_s + "#{check_digit}").to_i
    end
  end
end
