class PhoneNumber
  INVALID_NUMBER = '0000000000'

  def initialize raw_number
    @raw_number = raw_number
  end

  def number
    return INVALID_NUMBER if contains_letters?

    digits = get_digits

    return INVALID_NUMBER unless /^1?(?<phone_no>\d{10})$/ =~ digits

    phone_no
  end

  def area_code
    number[0..2]
  end

  def to_s
    phone_number = number

    area_code = phone_number[0..2]
    part1 = phone_number[3..5]
    part2 = phone_number[6..9]

    "(#{area_code}) #{part1}-#{part2}"
  end

private

  def contains_letters?
    @raw_number[/[a-z]/i]
  end

  def get_digits 
    @raw_number.scan(/\d/).join
  end
end
