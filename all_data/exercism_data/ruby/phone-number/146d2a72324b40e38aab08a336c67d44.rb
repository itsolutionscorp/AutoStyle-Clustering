class PhoneNumber
  attr_reader :number
  INVALID = "0000000000"

  def initialize raw_number
    @number = validate_format digits_in raw_number
  end

  def area_code
    number[0..2]
  end

  def subscriber_number
    "#{number[3..5]}-#{number[6..10]}"
  end

  def to_s
    "(#{area_code}) #{subscriber_number}"
  end

  private
  def digits_in str
    str.gsub(/\D/, '')
  end

  def validate_format number
    /\A1?(\d{10})\z/ =~ number ? $1 : INVALID
  end
end
