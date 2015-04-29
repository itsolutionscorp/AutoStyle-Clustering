class PhoneNumber
  attr_reader :number

  def initialize(raw)
    @number = PhoneNumberCleaner.new(raw).clean_number
  end

  def area_code
    @number[0,3]
  end

  def exchange_code
    @number[3,3]
  end

  def subscriber_number
    @number[6,4]
  end

  def to_s
    "(#{area_code}) #{exchange_code}-#{subscriber_number}"
  end
end

class PhoneNumberCleaner
  def initialize(raw)
    @raw = raw
  end

  def clean_number
    valid_number ? last_ten_numbers : default_number
  end

  private

  def valid_number
    /^1?\d{10}$/ =~ digits
  end

  def digits
    @raw.scan(/\d/).join
  end

  def last_ten_numbers
    digits[-10,10]
  end

  def default_number
    "0000000000"
  end
end
