class PhoneNumber
  attr_reader :number, :area_code
  DEFAULT_PHONE_NUMBER = '0000000000'

  def initialize(input)
    @number = _number input
    @area_code = @number[0..2]
  end

  def _number(_input)
    return DEFAULT_PHONE_NUMBER if _input.match /[a-z]/
    num = _input.gsub(/[^0-9]/, '')
    if num.size == 10
      return num
    elsif num.size == 11 && num[0] == '1'
      return num[1..-1]
    else
      return DEFAULT_PHONE_NUMBER
    end
  end

  def to_s
    "(#{@area_code}) #{@number[3..5]}-#{@number[6..10]}"
  end
end
