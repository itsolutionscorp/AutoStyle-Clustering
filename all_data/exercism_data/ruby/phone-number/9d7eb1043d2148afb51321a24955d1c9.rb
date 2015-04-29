class PhoneNumber
  attr_reader :number
  attr_reader :area_code

  def initialize(number_string)
    @number = number_string.scan(/[\da-zA-Z]/).join
    @number.replace(@number.slice(1,10)) if @number.length == 11 && @number[0] == '1'
    @number = "0000000000" if @number.to_i.to_s.length != 10
    @area_code = @number.slice(0..2)
  end

  def to_s
    base = @number.slice(3..5)
    tail = @number.slice(6..9)
    "(#{@area_code}) #{base}-#{tail}"
  end
end
