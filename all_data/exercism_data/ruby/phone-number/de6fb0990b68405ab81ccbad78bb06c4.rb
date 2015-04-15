class PhoneNumber
  attr_reader :number

  def initialize(raw_digits)
    @number = PhoneNumberCleaner.clean(raw_digits)
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
  def self.clean(raw_digits)
    return default_number unless valid_length? get_digits(raw_digits)
    return last_ten_digits_of(raw_digits) if valid_numbers? get_digits(raw_digits)
    default_number
  end

  def self.last_ten_digits_of(digits)
    get_digits(digits)[-10..-1]
  end

  def self.get_digits(raw_digits)
    raw_digits.scan(/\d/).join
  end

  def self.valid_length?(digits)
    digits.length == 11 || digits.length == 10
  end

  def self.valid_numbers?(digits)
    /^1?\d{10}$/ =~ digits
  end

  def self.default_number
    "0000000000"
  end
end
