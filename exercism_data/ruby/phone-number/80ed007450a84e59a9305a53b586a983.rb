class Phone
  attr_reader :number

  def initialize(input)
    @number = validates(clean(input))
  end

  def area_code
    number[0..2]
  end

  def to_s
    format_number
  end

  private

  def validates(phone)
    case
    when valid?(phone)
      phone[1..10]
    when invalid?(phone)
      invalid_number
    else
      phone
    end
  end

  def clean(phone)
    phone.gsub(/\D/, '')
  end

  def invalid_number
    "0000000000"
  end

  def valid?(phone)
    phone.length == 11 && phone[0] == "1"
  end

  def invalid?(phone)
    phone.length == 11 || phone.length == 9
  end

  def format_number
    "(#{area_code}) #{number[3..5]}-#{number[6..10]}"
  end
end
