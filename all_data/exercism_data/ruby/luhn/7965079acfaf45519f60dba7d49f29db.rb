class Luhn

  attr_reader :number
  def initialize(number)
    @number = number
  end

  def self.create(val)
    cks = Luhn.new(val*10).checksum % 10
    checkdigit = cks.zero? ? 0 : 10-cks
    val*10 + checkdigit
  end

  def addends
    build_addends(chrs(number))
  end

  def checksum
    build_checksum(addends)
  end

  def valid?
    validate(checksum)
  end

private
  def build_addends(digits)
    ary = []
    digits.each_with_index do |ch, i|
      factor = 2**(i%2)
      d = ch.to_i*factor
      d = d-9 if d > 9
      ary.unshift(d)
    end
    ary
  end

  def build_checksum(digits)
    digits.inject(0) {|a,res| res += a }
  end

  def validate(cksum)
    (cksum % 10).zero?
  end

  def chrs(num)
    num.to_s.chars.reverse
  end
end
