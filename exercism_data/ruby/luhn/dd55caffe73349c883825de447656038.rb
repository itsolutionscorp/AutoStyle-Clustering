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
    assemble_addends(chrs(number))
  end

  def checksum
    addends.inject(0, :+)
  end

  def valid?
    (checksum % 10).zero?
  end

private
  def assemble_addends(digits)
    ary = []
    digits.each_with_index do |ch, i|
      addend = create_addend(ch,i)
      ary.unshift(addend)
    end
    ary
  end

  def create_addend(ch,i)
    factor = 2**(i%2)
    d = ch.to_i*factor
    d > 9 ? d-9 : d
  end

  def chrs(num)
    num.to_s.chars.reverse
  end
end
