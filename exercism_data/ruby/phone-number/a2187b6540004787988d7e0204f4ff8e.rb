class Phone
  def initialize(number)
    @number = number
  end
  
  def number
    cleaned_digits
  end
  
  def area_code
    cleaned_digits[0..2]
  end

  def exchange
    cleaned_digits[3..5]
  end
  
  def subscriber
    cleaned_digits[6..9]
  end
  
  def to_s
    "(#{area_code}) #{exchange}-#{subscriber}"
  end
  
  private
  
  def cleaned_digits
    if long_distance?
      digits[1..-1]
    elsif with_area_code?
      digits
    else
      '0000000000'
    end
  end
  
  def long_distance?
    (digits.size == 11) && digits.start_with?('1')
  end
  
  def with_area_code?
    digits.size == 10
  end
  
  def digits
    @number.tr("() -.", "")    
  end
end
