class Phone
  
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
  
  def bad_number
    '0000000000'
  end
  
  def clean(digits)
    digits.gsub!(/\D/, '')
    digits = digits[1..-1] if national_format?(digits)
    
    digits.length == 10 ? digits : bad_number
  end
  
  def national_format?(number)
    number.length == 11 && number.start_with?("1")
  end

end
