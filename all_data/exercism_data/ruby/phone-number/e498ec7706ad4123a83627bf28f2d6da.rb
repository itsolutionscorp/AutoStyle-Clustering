class PhoneNumber
  attr_reader :phone_number

  def initialize(phone_number)
    @phone_number = phone_number
    sanitize_phone_number!
    sanitize_phone_number_digits!
  end

  def number
    phone_number
  end

  def area_code
    phone_number[0..2]
  end

  def to_s
    result = phone_number
    "(#{result[0,3]}) #{result[3,3]}-#{result[6,4]}"
  end

  private
  def sanitize_phone_number!
    @phone_number = phone_number.scan(/\d+/).join
  end

  def sanitize_phone_number_digits!
    @phone_number = if phone_number.length == 11 && phone_number.start_with?("1")
      remove_one_digit!
    elsif phone_number.length == 10
      phone_number
    else
      bad_number
    end
  end

  def remove_one_digit!
    @phone_number = phone_number[1..-1]
  end

  def bad_number
    "0" * 10
  end
end
