class PhoneNumber
  attr_reader :area_code
  
  def initialize(input)
    @string = input
    @parsed = parse(input)
    @area_code = @parsed[0,3]
    @first_three = @parsed [3,3]
    @last_four = @parsed [6,4]
  end


  def number
    begin
      validate
    rescue
      return "0000000000"
    end
    @parsed
  end

  def to_s
    "(#{area_code}) #{@first_three}-#{@last_four}"
  end

  private

  def parse(input)
      parsed = input.chars.select{|i| i=~ /\d/}.join('')
      if parsed.length == 11 && parsed[0] == '1'
        parsed = parsed[1..10]
      end
      return parsed
  end

  def validate
    if @parsed.length != 10 || @string =~ /[a-zA-Z]/
      raise ArgumentError
    end
  end
end
