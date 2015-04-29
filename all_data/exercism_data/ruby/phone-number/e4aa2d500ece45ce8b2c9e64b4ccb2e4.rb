class PhoneNumber
  attr_reader :number
  def initialize(number)
    @number = format_number(number)
    @number = invalid_number unless is_valid?
  end
  
  def format_number(number)
    number = number.gsub(/\D/, '')
    if number.length == 11 && (number.chars)[0].to_i == 1
      number = number.slice(1,number.length)
    end
    number
  end
  
  def invalid_number
    "0000000000"
  end
  
  def is_valid?
    @number.length == 10
  end
  
  def area_code
    @number.slice(0, 3)
  end
  
  def to_s
    "(#{area_code}) #{@number.slice(3,3)}-#{@number.slice(6,4)}"
  end
end
