module PhoneNumberProperties
  def length
    @number.length
  end

  def number
    @number
  end

  def area_code
    @number[0..2]
  end

  def prefix
    @number[3..5]
  end

  def line_number
    @number[6..9]
  end

  def international_number?
    length == 11 and @number.start_with? '1'
  end

  def to_s
    "(#{area_code}) #{prefix}-#{line_number}"
  end
end

class PhoneNumber
  include PhoneNumberProperties

  def initialize(number)
    @number = PhoneNumberCleaner.new(number).clean
  end
end

class PhoneNumberCleaner 
  include PhoneNumberProperties

  def initialize(number)
    @number = number
  end

  def clean
    strip_non_numerics

    @number = @number[1..-1] if international_number?
    @number = "0000000000" if length != 10
    @number
  end
 
  private

  def strip_non_numerics
    @number.gsub!(/\D/, '')
  end
end
