class PhoneNumber
  
  def initialize number
    @number = String(number)
  end
  
  def sanitized_number
    @number.gsub(/\W/, '')
  end
  
  def number
    begin
      case 
      when sanitized_number.size == 10
        sanitized_number
      when sanitized_number.size == 11 && sanitized_number.start_with?('11')
        sanitized_number[1..-1]
      else
        raise InvalidNumber
      end
    rescue InvalidNumber
      "0000000000"
    end
  end
  
  def area_code
    self.number[0..2]
  end
  
  def to_s
    number.gsub(/(?<area_code>\d{3})(?<city_code>\d{3})(?<number>\d{4})/, "(\\k<area_code>) \\k<city_code>-\\k<number>")
  end
  
  class InvalidNumber < StandardError; end
  
end
