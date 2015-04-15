class PhoneNumber
  attr_reader :digits

  def initialize(phone_number)
    @digits = phone_number.gsub(/\D/, '')
  end

  def to_s
    format_phone_number(number)
  end

  def number
    if valid_number_with_initial_one?
      remove_initial_one
    elsif !valid_number?
      "0000000000"
    else
      digits
    end
  end

  def area_code
    digits[0..2]
  end

  private

  def valid_number?
    digits.length == 10 
  end

  def remove_initial_one
    digits[1..-1]
  end

  def valid_number_with_initial_one?
    digits.length == 11 && digits[0] == "1"
  end

  def format_phone_number(number)
    "(#{number[0..2]}) #{number[3..5]}-#{number[6..-1]}" 
  end

end
