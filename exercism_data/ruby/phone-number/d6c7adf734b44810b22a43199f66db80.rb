class PhoneNumber
  attr_reader :number

  def initialize(num)
    @number = '0000000000'
    @number = parse(num)
  end

  def parse(num)
    num.match(/[^0-9()\-. ]/).nil? && num.scan(/\d/).length < 11 && num.scan(/\d/).length > 9 || num.length == 11 && num.scan(/\d/)[0] == '1' ? num.scan(/\d/)[-10..-1].join : @number
  end

  def area_code
    number[0..2]
  end

  def to_s
    "(#{number[0..2]}) #{number[3..5]}-#{number[6..-1]}"
  end
end
