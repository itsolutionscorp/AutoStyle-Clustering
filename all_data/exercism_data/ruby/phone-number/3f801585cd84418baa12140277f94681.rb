# Nice phone numbers
class PhoneNumber
  DEFAULT = '0000000000'

  # Clean given phone number
  def initialize(phone)
    @phone = phone.tr('. ()-', '')
  end

  # Filter wrong phone numbers and clean long stance calls
  def number
    if @phone.length == 11 && @phone[0] == '1'
      @phone[1..@phone.length]
    elsif @phone.match(/[a-zA-Z]/)
      DEFAULT
    elsif @phone.length <= 9 || @phone.length >= 11
      DEFAULT
    else
      @phone
    end
  end

  # Set area code
  def area_code
    number[0..2]
  end

  # Set 3 first numbers
  def prefix
    number[3..5]
  end

  # Set 4 last numbers
  def suffix
    number[6..9]
  end

  # Return well formatted number
  def to_s
    '(' + area_code + ') ' + prefix + '-' + suffix
  end
end
