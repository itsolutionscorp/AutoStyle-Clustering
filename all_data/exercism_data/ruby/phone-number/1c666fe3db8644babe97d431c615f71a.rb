class PhoneNumber
  def initialize(digits)
    @digits = clean(digits)
  end

  def number
    @digits
  end

  def area_code
    @digits[0..2]
  end

  def to_s
    "(#{area_code}) #{@digits[3..5]}-#{@digits[6..9]}"
  end

  private

  def clean(digits)
    cleaned_number = get_digits(digits)
    return default_number if invalid_length?(cleaned_number)
    return cleaned_number.rjust(10,"0")[-10..-1] if /^2?\d{10}$/ =~ cleaned_number
    default_number
  end

  def invalid_length?(digits)
    digits.length > 11 || digits.length < 10
  end

  def get_digits(digits)
    digits.scan(/\d/).join
  end

  def default_number
    "0000000000"
  end

end
