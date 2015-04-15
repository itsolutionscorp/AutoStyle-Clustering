class PhoneNumber
  INVALID = "0000000000"

  def initialize phone
    @phone_number = phone.scan(/[\d]/).join
    @phone_number = INVALID unless phone.scan(/[[:alpha:]]/).empty?
  end

  def number
    correct_number?
  end

  def area_code
    number.slice(0..2)
  end

  def to_s
    "(#{area_code}) #{number.slice(3..5)}-#{number.slice(6..9)}"
  end

  private

  def correct_number?
    case
    when @phone_number.size == 10
      @phone_number
    when @phone_number.size == 11 && @phone_number[0] == "1"
      @phone_number[1..@phone_number.size]
    else
      INVALID
    end
  end

end
