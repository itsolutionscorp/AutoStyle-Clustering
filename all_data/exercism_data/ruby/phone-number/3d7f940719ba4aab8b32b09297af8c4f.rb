class PhoneNumber
  
  def initialize number
    @number = number
  end
  
  def PhoneNumber arg
    number = String(arg).gsub(/\W/, '')
    case 
    when number.size == 10
      number
    when number.size == 11 && number.start_with?('11')
      number[1..-1]
    else
      raise InvalidNumber
    end
  end
  
  def number
    begin 
      PhoneNumber(@number) 
    rescue InvalidNumber
      "0" * 10
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
