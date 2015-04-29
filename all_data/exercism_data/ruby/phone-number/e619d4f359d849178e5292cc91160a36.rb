class PhoneNumber
  attr_reader :number

  def initialize(raw_digits)
    @number = PhoneNumberCleaner.new(raw_digits).clean_number
  end

  def area_code
    @number[0..2]
  end

  def local_number
    @number[3..9]
  end

  def to_s
    "(#{area_code}) #{local_number[0..2]}-#{local_number[3..6]}"
  end
end

class PhoneNumberCleaner
  def initialize(raw_digits)
    @digits = raw_digits
  end

  def clean_number
    valid_phone_number ? last_ten_digits : default_number
  end

  def get_only_digits
    @digits.scan(/\d/).join
  end

  def last_ten_digits
    get_only_digits[-10..-1]
  end

  def valid_phone_number
    /^1?\d{10}$/ =~ get_only_digits
  end

  def default_number
    "0000000000"
  end
end
