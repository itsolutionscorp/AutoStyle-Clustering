class PhoneNumber

  attr_reader :phone
  INVALID_NUMBER = "0000000000"

  def initialize phone
    @phone = phone
  end

  def number
    num = phone.gsub /\D/, ""
    return num[1..-1] if check_11_valid num 
    return INVALID_NUMBER if check_letters or check_length(num)
    num
  end

  def area_code
    number[0..2]
  end

  def to_s
    "(#{number[0..2]}) #{number[3..5]}-#{number[6..-1]}"
  end

  private

  def check_letters
    phone =~ /[a-zA-Z]/
  end

  def check_length num
    num.size != 10
  end

  def check_11_valid num
    num.size == 11 and num[0] == "1"
  end

end
