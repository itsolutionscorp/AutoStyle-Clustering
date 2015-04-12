class PhoneNumber
  
  def initialize number
    @number = String(number)
  end
  
  def santitized_number
    @number.gsub(/\W/, '')
  end
  
  def number
    begin
      case 
      when santitized_number.size == 10
        santitized_number
      when santitized_number.size == 11 && santitized_number.start_with?('11')
        santitized_number[1..-1]
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