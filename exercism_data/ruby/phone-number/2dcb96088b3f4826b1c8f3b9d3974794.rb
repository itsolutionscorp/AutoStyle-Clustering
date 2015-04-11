class PhoneNumber
  
  attr_reader :number
  
  def initialize number
    @number = PhoneNumber(number)
  end
  
  def PhoneNumber arg
    number = String(arg).gsub(/\W/, '')
    begin
      case 
      when number.size == 10
        number
      when number.size == 11 && number.start_with?('11')
        number[1..-1]
      else
        raise InvalidNumber
      end
    rescue InvalidNumber
      "0000000000"
    end
  end
  
  def area_code
    number[0..2]
  end
  
  def exchange_code
    number[-7..-5]
  end
  
  def subscriber_number
    number[-4..-1]
  end
  
  def to_s
    "(#{area_code}) #{exchange_code}-#{subscriber_number}"
  end
  
  class InvalidNumber < StandardError; end
  
end
