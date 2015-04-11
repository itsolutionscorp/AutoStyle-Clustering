class Phone
  attr_reader :number
  
  def initialize(number)
    @number = clean number
  end
  
  def area_code
    @number[0,3]
  end
  
  def to_s
    sprintf "(%s) %s-%s", area_code, @number[3,3], @number[6,4]
  end
  
  private
  
  def clean(num)
    digits = num.scan(/\d/).join
    if ((digits.length == 11) && (digits[0] == '1'))
      digits[1,10]
    elsif (digits.length == 10)
      digits
    else
      ([0] * 10).join
    end
  end
end
