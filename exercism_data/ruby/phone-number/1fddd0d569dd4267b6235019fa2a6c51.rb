# phone_number.rb
# Phone number exercise

class PhoneNumber
  def initialize(numstring)
    numstring.gsub!(/[^\d]/,'')
    l = numstring.length
    
    @num=case
    when l==10
      numstring
    when l==11 && numstring[0]=='1'
      numstring[1..-1]
    else
      "0000000000"
    end
  end
  
  def number
    @num
  end
  
  def to_s
    "(#{@num[0..2]}) #{@num[3..5]}-#{@num[6..10]}"
  end
  
  def area_code
    @num[0..2]
  end
end
