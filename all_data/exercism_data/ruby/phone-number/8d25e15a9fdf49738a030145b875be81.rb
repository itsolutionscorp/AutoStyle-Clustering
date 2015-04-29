class PhoneNumber
  attr_reader :phone_number
  alias_method :number, :phone_number

  def initialize( raw_phone_number )
    cleaned_number = clean_phone_number( raw_phone_number ) 
    if valid_phone_number?( cleaned_number ) 
      @phone_number = cleaned_number 
      make_phone_number_10_digits_long
    else
      @phone_number = "0" * 10
    end
  end

  def area_code
    @phone_number[0..2]
  end

  def to_s
    "(" + @phone_number[0..2] + ") " + @phone_number[3..5] + "-" + @phone_number[6..9] 
  end

  def make_phone_number_10_digits_long
    if @phone_number.length == 11
      @phone_number = @phone_number[1..10]
    end
  end

  def valid_phone_number?( number="" )
    case number.length
    when 10 
      true
    when 11 then 
      number[0] == "1" ? true : false
    else
      false
    end
  end

  def clean_phone_number( raw_phone_number )
    cleaned_number = ""
    raw_phone_number.chars.each { |d| cleaned_number << d  unless d.match(/[0-9]/).nil? }
    cleaned_number    
  end
end
