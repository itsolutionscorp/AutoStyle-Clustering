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
  
  def clean(digits)
    digits.gsub!(/\D/, '')
    digits = digits[1..-1] if digits.length == 11 && digits.start_with?("1")
    digits = '0000000000' unless digits.length == 10
    
    digits
  end

end
