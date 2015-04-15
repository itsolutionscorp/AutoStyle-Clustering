class PhoneNumber
  def initialize(input)
    @input = input
  end

  def number
    strip
    if long_enough? && short_enough?
      @number
    else
      "0000000000"
    end
  end

  def area_code
  	number
  	@local_number = "#{@number[3..5]}-#{@number[6..9]}"
    @area_code = "#{@number[0..2]}"
  end

  def to_s
  	area_code
    @pretty_number =  "(#{@area_code}) #{@local_number}"
  end

  private

  def strip
    @number = @input.gsub(/[^[:alnum:]]/, "")
  end

  def long_enough?
    @number.length > 9
  end

  def short_enough?
    if @number.length < 11
      true
    elsif @number.length == 11 && @number[0] == "1"
      @number = @number[1..10]
    end
  end

end
