class PhoneNumber

  attr_accessor :number

  def initialize(input)
    @number = clean(input)
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

  def to_s
    "(#{area_code}) #{prefix}-#{line_number}"
  end

  private
  
  def country_code
    '1'
  end
  
  def bad_number
    '0000000000'
  end

  def clean(digits)
    digits.gsub!(/\D/, '')
    digits = digits[country_code.length..-1] if national_format?(digits)

    digits.length == 10 ? digits : bad_number
  end

  def national_format?(number)
    number.start_with?(country_code) && number.length == country_code.length + 10 
  end

end
