module IntegerDigits
  refine Integer do
    def digits
      return [abs] if abs < 10
      [*abs.div(10).digits, abs.modulo(10)]
    end
  end
end

class Luhn
  using IntegerDigits

  def self.create(number)
    (0..9).map { |x| new(number * 10 + x) }.find(&:valid?).number
  end

  attr_reader :number

  def initialize(number)
    @number = number
  end

  def addends
    number.digits.reverse.map.with_index do |d, i|
      d *= 2 if i.odd?
      d -= 9 if d > 9
      d
    end.reverse
  end

  def checksum
    addends.reduce(:+)
  end

  def valid?
    checksum.modulo(10).zero?
  end
end
