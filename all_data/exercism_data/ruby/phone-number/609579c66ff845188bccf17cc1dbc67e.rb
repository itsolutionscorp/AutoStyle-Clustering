class PhoneNumber
  attr_reader :number

  def initialize(dirty_number)
    @number = sanitize(dirty_number)
  end

  def area_code
    number[0..2]
  end

  def to_s
    "(#{area_code}) #{exchange}-#{station}"
  end

  private

  def sanitize(dirty_number)
    # validated_number(get_digits(dirty_number))
    dirty_number.tap{|n| break get_digits(n)}.tap{|n| break validated_number(n)}
  end

  def exchange
    number[3..5]
  end

  def station
    number[6..10]
  end

  def get_digits(dirty_number)
    dirty_number.delete('^0-9')
  end

  def validated_number(digits)
    return digits                      if correct_length?(digits)
    return remove_country_code(digits) if has_country_code?(digits)
    bad_number
  end

  def correct_length?(digits)
    digits.length == 10
  end

  def remove_country_code(digits)
    digits[1..-1]
  end

  def has_country_code?(digits)
    digits.length == 11 && digits.start_with?('1')
  end

  def bad_number
    '0' * 10
  end
end
