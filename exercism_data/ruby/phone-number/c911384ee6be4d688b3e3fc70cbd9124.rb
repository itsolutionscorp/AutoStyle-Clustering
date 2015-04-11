class PhoneNumber
  NULL_PHONE_NUMBER = "0000000000"

  AREA_CODE_LENGTH = 3
  NUMBER_SEPARATOR_POSITION = 3

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
    @number[0...AREA_CODE_LENGTH]
  end

  def to_s
    "(#{area_code}) #{first_part}-#{second_part}"
  end

  private

  def first_part
    @number[AREA_CODE_LENGTH...(AREA_CODE_LENGTH+NUMBER_SEPARATOR_POSITION)]
  end

  def second_part
    @number[(AREA_CODE_LENGTH+NUMBER_SEPARATOR_POSITION)..-1]
  end
end
