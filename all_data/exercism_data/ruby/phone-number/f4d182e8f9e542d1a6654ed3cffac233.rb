class PhoneNumber
  attr_reader :number

  def initialize(number)
    @number = validate(number)
  end

  def area_code
    @number[0..2]
  end

  def to_s
    "(#{area_code}) #{@number[3..5]}-#{@number[6..9]}"
  end

  private

  def validate(number)
    return bad_number if number =~ /[a-z]/i

    number = keep_digits_only(number)
    length = number.length

    if length == 11 && number[0].to_i == 1
      number[1..10]
    elsif length != 10
      bad_number
    else
      number
    end
  end

  def keep_digits_only(number)
    number.chars.each_with_object('') do |char, digits|
      digits << char if char =~ /\d/
    end
  end

  def bad_number
    "0000000000"
  end
end
