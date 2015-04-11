class Phone
  attr_reader :number

  def initialize(input)
    @number = validates_phone(input)
  end

  def area_code
    number[0..2]
  end

  def to_s
    format_number
  end

  private

  def validate_phone(number)
    phone = clean(number)

    if phone.length == 10
      phone
    elsif phone.length == 11 && phone[0] == "1"
      phone[1..10]
    else
      invalid_phone
    end
  end

  def clean(phone)
    phone.gsub(/\D/, '')
  end

  def invalid_phone
    "0000000000"
  end

  def format_number
    "(#{area_code}) #{number[3..5]}-#{number[6..10]}"
  end
end
