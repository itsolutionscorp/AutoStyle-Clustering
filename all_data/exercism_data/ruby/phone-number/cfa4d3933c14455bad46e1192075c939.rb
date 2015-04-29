class Phone
  INVALID_NUMBER = "0000000000".chars

  attr_reader :number
  def initialize(input)
    digits = input.scan(/\d/)
    number = case digits.size
    when 10
      digits
    when 11      
      digits.drop(1) if digits.first == "1"
    end || INVALID_NUMBER
    @number = number.join
  end

  def area_code
    number[0,3]
  end

  def to_s
    "(#{area_code}) #{number[3,3]}-#{number[6,4]}"
  end
end
