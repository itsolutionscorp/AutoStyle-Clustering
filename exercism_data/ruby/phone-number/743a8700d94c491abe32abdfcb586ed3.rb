class PhoneNumber
  attr_reader :area_code, :number

  def initialize(string)
    parse(string)
  end

  def to_s
    "(#{area_code}) #{number[3..5]}-#{number[6..-1]}"
  end

  private

  def parse(string)
    number = string.scan(/\d/).join
    if valid_number?(number)
      if number.length == 10
        @number = number
      else
        @number = number[1..-1]
      end
    else
      @number = "0"*10
    end
    @area_code = @number[0..2]
  end

  def valid_number?(number)
    number.length == 10 || (number.length == 11 && number.start_with?("1"))
  end

end
