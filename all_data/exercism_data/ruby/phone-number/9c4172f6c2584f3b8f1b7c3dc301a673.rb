class Phone
  def initialize(number)
    @number = number
  end

  def number
    remove_noise(@number)
    if valid?(@number)
      
      if extra_digit?(@number)
        remove_extra_digit(@number)
      else
        @number
      end

    else
      show_format
    end
  end

  def area_code
    @number[0..2]
  end

  def to_s
    "(#{@number[-10..-8]}) #{@number[-7..-5]}-#{@number[-4..-1]}"
  end

  def remove_noise(number)
    number.gsub!(/[-()\s\.]/, '')
  end

  def valid?(number)
    number.length == 10 or extra_digit?(number)
  end

  def extra_digit?(number)
    number.length == 11 and number[0] == "1"
  end

  def remove_extra_digit(number)
    number[1..-1]
  end

  def show_format
    "0000000000"
  end
end
