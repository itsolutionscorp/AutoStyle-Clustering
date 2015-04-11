class PhoneNumber
  attr_reader :number
  INVALID = "0000000000"

  def initialize raw_number
    valid = /\A1?(?<number>\d{10})\z/ =~ raw_number.gsub(/\D/, '')
    @number = valid ? number : INVALID
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
end
