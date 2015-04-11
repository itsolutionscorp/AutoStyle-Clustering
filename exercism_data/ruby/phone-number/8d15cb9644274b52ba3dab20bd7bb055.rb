class PhoneNumber
  def initialize input_number
    @input_number = input_number
  end
  
  def number
    @number ||= validated_number
  end
  
  def prefix
    number[3..5]
  end
  def linenumber
    number[6..10]
  end
  
  def area_code
    number[0..2]
  end
  
  def to_s
    "#{area_code} #{prefix}-#{linenumber}"
  end
  
  private
  
  def validated_number
    if clean_input.length == 10
      clean_input
    else
      "0000000000"
    end
  end
  
  def digits
    @digits ||= @input_number.scan(/\d+/).join
  end
  
  def us_number?
    digits[0] =='1' && digits.length == 11
  end
  
  def clean_input
    if us_number?
      digits.reverse.chop.reverse
    else
      digits
    end
  end
end
