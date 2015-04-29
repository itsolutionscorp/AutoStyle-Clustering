class PhoneNumber
  def initialize phone
    valid_number?(phone)
  end

  def number
    correct_number?
  end

  def area_code
    number.slice(0..2)
  end

  def to_s
    first_part = number.slice(3..5)
    second_part = number.slice(6..9)
    "(#{area_code}) #{first_part}-#{second_part}"
  end

  private

  def valid_number? phone
    if phone.scan(/[[:alpha:]]/).empty?
      @phone_number = phone.scan(/[\d]/).join
    else
      @phone_number = "invalid"
    end
  end

  def correct_number?
    case
    when @phone_number.size < 10 || @phone_number.size > 11
      "0000000000"
    when @phone_number.size == 11
      format_number
    else
      @phone_number
    end
  end

  def format_number
    @phone_number[0] == "1" ? @phone_number[1..@phone_number.size] : "0000000000"
  end
end
