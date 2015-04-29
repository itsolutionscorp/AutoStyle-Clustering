class Fixnum
  def split
    to_s.split('').map(&:to_i)
  end
end

class Luhn
  def self.create(num)
    checker = Luhn.new(num)
    return if checker.valid?
    numbers = (0..9).each
    loop do
      checker.numbers << numbers.next
      break if checker.valid?
      checker.numbers.pop
    end
    checker.numbers.join.to_i
  end

  attr_reader :numbers

  def initialize(num)
    @numbers = num.split
  end

  def addends
    addend_array = numbers.dup.reverse
    (1...numbers.length).step(2) do |index|
      addend_array[index] *= 2
      addend_array[index] -= 9 if addend_array[index] > 9
    end
    addend_array.reverse
  end

  def checksum
    addends.reduce(:+)
  end

  def valid?
    checksum % 10 == 0
  end
end
