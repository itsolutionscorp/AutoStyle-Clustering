class PhoneNumber

  attr_reader :number

  def initialize number
    refined = number.gsub /\W+/,""
    if refined[/[a-zA-Z]/]
      @number = "0000000000"
    elsif refined.length != 10
      if refined.length == 11 && refined.start_with?("1")
        @number = refined.sub "1",""
      else
        @number = "0000000000"
      end
    else
      @number = refined
    end
  end

  def area_code
    @number[0,3]
  end

  def to_s
    "(#{area_code}) #{@number[3,3]}-#{@number[6,4]}"
  end

end
