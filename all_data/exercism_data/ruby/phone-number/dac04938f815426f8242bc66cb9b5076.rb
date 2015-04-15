class PhoneNumber
  attr_reader :phone_no
  
  def initialize(phone_no)
    @phone_no = phone_no.gsub(/[()-\.\s]/,'')
  end
  
  def number
    if bad_number
      default_number
    elsif eleven_digits_starts_with_1?
      phone_no[1..-1]
    else
      phone_no
    end
  end
  
  def area_code
    number[0..2]
  end
  
  def to_s
    ph = number
    "(" + ph[0..2] + ") " + ph[3..5] + "-" + ph[6..9]
  end
  
  private
  
  def bad_number
    phone_num_length < 10 || phone_num_length > 11 || phone_no.match(/[a-zA-Z]/) || (phone_num_length == 11 && !phone_no.start_with?("1"))
  end
  
  def default_number
    "0000000000"
  end
  
  def eleven_digits_starts_with_1?
    phone_num_length == 11 && phone_no.start_with?("1")
  end
  
  def phone_num_length
    phone_no.length
  end
end
