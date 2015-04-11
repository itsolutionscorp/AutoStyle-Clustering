class PhoneNumber
  NULL_PHONE_NUMBER = "0000000000"

  attr_reader :number

  def initialize(phone_number)
    phone_number = phone_number.gsub(/[^\d]/, '')

    if phone_number.length == 10
      @number = phone_number
    elsif phone_number.length == 11 && phone_number[0] == "1"
      @number = phone_number[1..-1]
    else
      @number = NULL_PHONE_NUMBER
    end
  end

  def area_code
    @number[0..2]
  end

  def to_s
    "(#{area_code}) #{@number[3..5]}-#{@number[6..-1]}"
  end
end
