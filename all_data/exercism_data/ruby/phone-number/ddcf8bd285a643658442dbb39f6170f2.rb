class PhoneNumber
  attr_accessor :phone_number
  def initialize(phone_number)
    @phone_number = phone_number.split(//)
  end

  def number
    return "0" * 10 if invalid_number
    if digit_count.eql? 11
      return "0" * 10 unless valid_starting_digit
      phone_number.delete_at(0)
    end
    phone_number.map { |c| c if c =~ /\d/ }.join
  end

  def area_code
    number[0..2]
  end
  
  def to_s
    "(" + area_code + ") " + number[3..5] + "-" + number[6..-1]
  end

  private
  def invalid_number
    digit_count < 10 || digit_count > 11 || alpha_count > 0
  end

  def valid_starting_digit
    phone_number.first.eql? "1"
  end

  def digit_count
    phone_number.count { |c| c =~ /\d/ }
  end

  def alpha_count
    phone_number.count { |c| c =~ /[a-z]/ }
  end
end
