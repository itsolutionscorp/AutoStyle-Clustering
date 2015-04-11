class PhoneNumber
  def initialize input_number
    @input_number = input_number
  end
  
  def number
    @number ||= validated_number
  end
  
  def area_code
    number[0..2]
  end
  
  def to_s
    "(#{number[0..2]}) #{number[3..5]}-#{number[6..10]}"
  end
  
  private
  
  def validated_number
    if clean_input.length == 10
      clean_input
    else
      "0000000000"
    end
  end
  
  def clean_input
    @clean_input ||= begin
      digits = @input_number.scan(/\d+/).join
      if digits[0] =='1' && digits.length == 11
        digits[1..10]
      else
        digits
      end
    end
  end
end
