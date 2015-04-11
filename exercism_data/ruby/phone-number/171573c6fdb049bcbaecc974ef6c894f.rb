class PhoneNumber

  INVALID_NUMBER_IDENTIFIER = "0000000000"

  def initialize(phone_number)
    @phone_number = phone_number
  end

  def number
    valid? ? parsed_number : INVALID_NUMBER_IDENTIFIER
  end

  def area_code
    parsed_number.slice(0..2)
  end

  def to_s
    "(#{area_code}) #{nxx}-#{line}"
  end

  private

  def valid?
    valid_numeric? && valid_length?
  end

  def valid_numeric?
    true unless @phone_number  =~ (/[a-zA-Z]/)
  end

  def valid_length?
    parsed_number.length == 10
  end

  def parsed_number
    @parsed ||= @phone_number.scan(/[\d]+/).join
    if @parsed.length == 11 && @parsed.start_with?("1")
      @parsed.slice!(0)
    end
    @parsed
  end

  def nxx
    parsed_number.slice(3..5)
  end

  def line
    parsed_number.slice(6..9)
  end
end
