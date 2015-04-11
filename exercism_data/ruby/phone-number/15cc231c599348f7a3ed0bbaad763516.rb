class PhoneNumber
  INVALID = "0" * 10
  def initialize digits
    @num = digits
  end 
 
  def prune_number
    return INVALID if @num =~ /[A-Za-z]/
    digits = @num.gsub(/[^0-9]/,'')
    num_digits = digits.length
    begins_with_1 = true if digits.chars.first == '1'
       
    sms = case
      when num_digits == 11 && begins_with_1 then digits[1..10]
      when num_digits == 10 then digits
      else
        INVALID 
      end
    sms
  end 

  def number
     prune_number
  end
  
  def area_code
    pn = prune_number
    pn[0..2]
  end

  def to_s
    pn = prune_number
    "(#{area_code}) #{pn[3..5]}-#{pn[6..9]}"
  end
end
