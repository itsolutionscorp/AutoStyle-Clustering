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
    valid_formatter = clean_input.scan(/1?(.{10})(.*)/).flatten
    if valid_formatter[1] == ''
      valid_formatter[0]
    else
      "0000000000"
    end
  end
  
  def clean_input
    @input_number.scan(/\d+/).join
  end
end
