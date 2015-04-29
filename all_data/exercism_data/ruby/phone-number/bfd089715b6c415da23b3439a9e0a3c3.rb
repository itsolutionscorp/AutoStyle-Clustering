class PhoneNumber
  attr_reader :number

  def initialize(input)
    @number = parse(input)
  end

  def parse(input)
    result = 0
    digits = input.gsub(/\D/, '')
    match = digits.match(/^1?(\d{10})$/)
    if match && match.length == 2
      result = match[1].to_i
    end
    '%010d' % result
  end

  def area_code
    number[0..2]
  end

  def exchange
    number[3..5]
  end

  def extension
    number[6..10]
  end

  def to_s
    "(#{area_code}) #{exchange}-#{extension}"
  end
end
