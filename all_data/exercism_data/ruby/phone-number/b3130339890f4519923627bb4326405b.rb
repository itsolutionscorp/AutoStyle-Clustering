class PhoneNumber

  attr_reader :number, :area_code

  INVALID_NUMBER = '0' * 10

  def initialize(number)
    @number = validate(clean(number))
    @area_code = @number[0..2]
  end

  def clean(number)
    number.gsub(/[\s()-.]/,'')
  end

  def validate(number)
    return INVALID_NUMBER unless numerical?(number)
    if number.length == 10
      number
    elsif number.length == 11 && number[0] == '1'
      number[1..10]
    else
      INVALID_NUMBER
    end
  end

  def numerical?(number)
    number.scan(/[^0-9]/).empty?
  end

  def to_s
    "(#{@number[0..2]}) #{@number[3..5]}-#{@number[6..9]}"
  end

end
