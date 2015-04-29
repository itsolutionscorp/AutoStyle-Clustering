class PhoneNumber

  def initialize number

    @number = number.gsub(/[^0-9a-z]/, '')

    if @number.gsub(/[^0-9]/, '').length < 10
      @phone_number = "0000000000"
    elsif @number.length > 11
      @phone_number = "0000000000"
    elsif @number.length == 11 && @number[0] != '1'
      @phone_number = "0000000000"
    elsif @number.length == 11
      @phone_number = @number.slice(1, 10)
    else
      @phone_number = @number
    end

  end

  def number

    @phone_number

  end

  def area_code

    @phone_number.slice(0..2)

  end

  def to_s

    "(#{area_code}) #{@phone_number.slice(3..5)}-#{@phone_number.slice(6..9)}"

  end

end
