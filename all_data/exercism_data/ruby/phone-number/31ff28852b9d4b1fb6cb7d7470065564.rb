class Phone
  attr_reader :phone_number

  def initialize(input)
    @phone_number = clean(input)
  end

  def number
    validates_phone
  end

  def area_code
    phone_number[0..2]
  end

  def to_s
    format_phone
  end

  private

  def validates_phone
    case
    when eleven_digits_and_first_is_one?
      phone_number[1..10]
    when eleven_digits? || nine_digits?
      invalid_number
    else
      phone_number
    end
  end

  def clean(phone)
    phone.scan(/\d/).join
  end

  def invalid_number
    "0000000000"
  end

  def eleven_digits_and_first_is_one?
    phone_number.length == 11 && phone_number[0] == "1"
  end

  def eleven_digits?
    phone_number.length == 11
  end

  def nine_digits?
    phone_number.length == 9
  end

  def format_phone
    "(#{area_code}) #{number[3..5]}-#{number[6..10]}"
  end
end
