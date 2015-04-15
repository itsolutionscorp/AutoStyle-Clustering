class PhoneNumber
  DEFAULT_NUMBER = "0000000000"
  VALID_NON_NUMERIC_CHARACTERS = " ()-."
  OPTIONAL_PREFIX = "1"

  def initialize(number)
    @sanitized_number = sanitize(number)
  end

  def number
    if valid?
      number_without_prefix
    else
      DEFAULT_NUMBER
    end
  end

  def area_code
    number_without_prefix[0, 3]
  end

  def to_s
    "(#{area_code}) #{number_without_prefix[3, 3]}-#{number_without_prefix[6, 4]}"
  end

  private

  def number_without_prefix
    @number_without_prefix ||=
      if @sanitized_number.length == 11
        @sanitized_number[1..-1]
      else
        @sanitized_number
      end
  end

  def sanitize(number)
    number.delete(VALID_NON_NUMERIC_CHARACTERS)
  end

  def valid?
    valid_ten_digit_number? || valid_eleven_digit_number?
  end

  def valid_ten_digit_number?(number = @sanitized_number)
    number =~ /^\d{10}$/
  end

  def valid_eleven_digit_number?
    @sanitized_number[0] == OPTIONAL_PREFIX &&
      valid_ten_digit_number?(@sanitized_number[1..-1])
  end
end
