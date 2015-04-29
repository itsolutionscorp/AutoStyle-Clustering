class PhoneNumber
  attr_reader :number
  INVALID_NUMBER = '0000000000'

  def initialize number
    @number = clean_number(number)
  end

  def area_code
    @number[0..2]
  end

  def to_s
    "(#{area_code}) #{prefix}-#{identifier}"
  end

  private

  def clean_number dirty_number
    cleaning = dirty_number.gsub(/\D/, '')
    return INVALID_NUMBER unless valid?(cleaning)
    cleaning.chars.last(10).join
  end

  def valid? dirty_number
    dirty_number.size == 10 ||
    dirty_number.size == 11 && dirty_number.start_with?('1')
  end

  def prefix
    @number[3..5]
  end

  def identifier
    @number[6..9]
  end
end
