class PhoneNumber
  class InvalidPhoneNumberError < StandardError; end

  attr_reader :number

  def initialize(number)
    @number = number
    clean_number
  end

  def area_code
    number[0..2]
  end

  def to_s
    "(#{area_code}) #{number[3..5]}-#{number[6..10]}"
  end

  private

  def digits(number)
    number.gsub(/\D/, '')
  end

  def clean_number
    validate_digits
    @number = digits(number)
    validate_length
  rescue InvalidPhoneNumberError
    @number = '0' * 10
  end

  def validate_digits
    raise InvalidPhoneNumberError if number =~ /[^\d().\s-]/
  end

  def validate_length
    if number.length < 10
      raise InvalidPhoneNumberError
    elsif number.length == 11
      raise InvalidPhoneNumberError unless number.start_with?('1')
      @number = number[1, 10]
    elsif number.length > 11
      raise InvalidPhoneNumberError
    end
  end
end
