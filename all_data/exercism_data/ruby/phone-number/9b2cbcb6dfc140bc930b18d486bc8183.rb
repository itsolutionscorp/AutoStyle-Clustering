class PhoneNumber
  attr_reader :number, :area_code
  def initialize number
    @number = number.chars.map { |ch| ch.sub /[\s.()-]/, '' }.join
    if valid_number?
      @number = @number[1..-1] if @number.length == 11
      @area_code = @number[0, 3]
    else
      @number = "0000000000"
    end
  end

  def to_s
    "(#{@area_code}) #{@number[3,3]}-#{@number[6, 4]}"
  end

  private

  def valid_number?
    if @number =~ /\D/
      false 
    elsif @number.length == 11
      @number[0] == "1"
    else 
      @number.length == 10 
    end
  end               
end
