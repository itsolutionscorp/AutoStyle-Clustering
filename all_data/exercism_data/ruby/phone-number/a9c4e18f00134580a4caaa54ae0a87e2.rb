class Phone

  attr_accessor :phone_number 
  def initialize(phone_number)
    @phone_number = phone_number
  end  

  def number
    number_validator(phone_number.to_s.gsub(/([(.)-])/,'').gsub(/\s+/, ""))
  end

  def to_s
    "(#{area_code})" + ' ' +  phone_number.to_s[3..5] + "-" +  phone_number.to_s[6..10]
  end

  def area_code
    zip_code = phone_number.to_s[0..2]
    return "#{zip_code}" 
  end

  private

  def number_validator(phone_number)
    return  phone_number.to_s[1..10] if phone_number.to_s.size > 10  && phone_number.start_with?("1")
    return phone_number.to_s if phone_number.to_s.size == 10
  else
    return "0" * 10
  end
end
