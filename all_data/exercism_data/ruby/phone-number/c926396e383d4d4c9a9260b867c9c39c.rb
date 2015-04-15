class PhoneNumber
  def initialize input_number
    @input_number = input_number
  end
  def number
    @number = if digits.length == 11
      if digits[0] == '1'
        digits[1..11]
      else
        "0000000000"
      end
    elsif digits.length == 9 || digits.length > 11
      "0000000000"
    else
      digits
    end
  end
  
  def area_code
    number[0..2]
  end
  
  def to_s
    "(#{number[0..2]}) #{number[3..5]}-#{number[6..10]}"
  end
  
  private
  def digits
    @digits ||= @input_number.scan(/\d+/).join
  end
end
