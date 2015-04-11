class PhoneNumber
  def initialize(string)
    @number_string = string[/[A-Za-z]/] ? "0000000000" : string.delete("^0-9")
  end

  def number
    if string_length == 10 
      @number_string
    elsif string_length == 11 && @number_string[0] == "1" 
      @number_string[1..-1]
    else '0000000000'
    end
  end

  def string_length
    @number_string.length
  end

  def area_code
    @number_string[0..2]
  end

  def to_s
    if string_length == 11
      @number_string = @number_string[1..-1]
    end
    
    "(#{@number_string[0..2]}) #{@number_string[3..5]}-#{@number_string[6..9]}"
  end
end
