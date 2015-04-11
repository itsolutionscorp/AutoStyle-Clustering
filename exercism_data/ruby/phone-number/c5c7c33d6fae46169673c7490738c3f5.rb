class PhoneNumber
  attr_reader :number

  def initialize(input)
    @number = "0" * 10
    input.gsub!(/\W/,"")
    @number = input unless input =~ (/[a-zA-Z]/) || !(input.length == 10 || input.length == 11 && input.start_with?( "1") )
    @number[0] = '' if @number.length == 11
  end

  def area_code
    @number[0,3]
  end

  def to_s
    "(#{@number[0,3]}) #{@number[3,3]}-#{@number[6,4]}"
  end

end
