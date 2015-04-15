# Nice phone numbers
class PhoneNumber
  DEFAULT = '0000000000'

  def initialize(phone)
    @phone = phone.tr('. ()-', '')
  end

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

  def area_code
    number[0..2]
  end

  def to_s
    "(#{number[0..2]}) #{number[3..5]}-#{number[6..9]}"
  end
end
