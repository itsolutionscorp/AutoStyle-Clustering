class PhoneNumber

  def initialize(number)
    @number = number
  end

  def number
    clean(@number)
  end

  def area_code
    number[0,3]
  end

  def to_s
    "(" + area_code + ") " + number[3,3] + "-" + number[6,4]
  end

  private

  def clean(number)
    if number.nil?
      number = "0000000000"
    else
      number = number.scan(/[0-9]/).join
      if number.length == 11 && number.start_with?("1")
        number = number[1..-1]
      elsif number.length != 10
        number = "0000000000"
      else
        return number
      end
    end
    
  end
end
