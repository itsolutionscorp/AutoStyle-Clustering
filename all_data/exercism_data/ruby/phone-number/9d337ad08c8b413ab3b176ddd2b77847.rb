class PhoneNumber
  def initialize(phone_number)
    @phone_number = phone_number
  end

  def number
    digits = @phone_number.gsub(/[^\d]/,'')
    alpha_digits = @phone_number.gsub(/[^a-zA-Z0-9]/,'')
    if digits.length == 10 && digits == alpha_digits
      @phone_number = digits
    elsif digits.length == 11 && digits[0] == '1'
      @phone_number = digits[1, digits.length-1]
    else
      @phone_number = '0000000000'
    end

    @phone_number
  end

  def area_code
    self.number.slice(0,3)
  end

  def to_s
    num = self.number
    "(#{num[0,3]}) #{num[3,3]}-#{num[6,4]}"
  end
end
