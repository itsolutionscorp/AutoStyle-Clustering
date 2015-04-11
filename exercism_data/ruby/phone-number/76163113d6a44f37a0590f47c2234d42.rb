class PhoneNumber
  attr_reader :phone_number

  def initialize(phone_number)
    @phone_number = phone_number.gsub(/\D/, '')
  end

  def number
    if valid_number_with_initial_one?
      remove_initial_one
    elsif invalid?
      "0000000000"
    else
      phone_number
    end
  end

  def area_code
    phone_number[0..2]
  end

  def to_s
    if valid_number_with_initial_one?
      format_phone_number(remove_initial_one)
    else
      format_phone_number(phone_number)
    end 
  end

  private

  def valid_number?
    phone_number.length == 10 
  end

  def remove_initial_one
    phone_number[1..-1]
  end

  def valid_number_with_initial_one?
    phone_number.length == 11 && phone_number[0] == "1"
  end

  def invalid?
    !valid_number? && !valid_number_with_initial_one?
  end

  def format_phone_number(number)
    "(#{number[0..2]}) #{number[3..5]}-#{number[6..-1]}" 
  end

end
