class PhoneNumber
  attr_reader :number

  INVALID = "0000000000"

  def initialize(number)
    @number = parse(number)
  end

  def area_code
    number[0..2]
  end

  def to_s
    "(#{area_code}) #{number[3..5]}-#{number[6..9]}"
  end

  private

  def parse(number)
    return INVALID if number.match(/[a-z]/i)
    number = number.scan(/\d/).join

    if number.length == 10
      number
    elsif number.length == 11 && number.start_with?("1")
      number[1..10]
    else
      INVALID
    end
  end
end
