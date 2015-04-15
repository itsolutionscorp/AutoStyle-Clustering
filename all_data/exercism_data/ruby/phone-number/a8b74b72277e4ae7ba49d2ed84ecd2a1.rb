class PhoneNumber
  INVALID = "0000000000"

  attr_reader :number

  def initialize phone
    @number = phone.scan(/[\d]/).join
    @number = valid?(phone)
  end

  def area_code
    number.slice(0..2)
  end

  def to_s
    "(#{area_code}) #{number.slice(3..5)}-#{number.slice(6..9)}"
  end

  private

  def valid? phone
    phone.scan(/[[:alpha:]]/).empty? ? correct_number? : INVALID
  end

  def correct_number?
    case
    when @number.size == 10
      @number
    when @number.size == 11 && @number[0] == "1"
      @number[1..-1]
    else
      INVALID
    end
  end

end
