class Phone

  def initialize digits
    @digits = digits.gsub(/[^0-9]/, "")
  end

  def number
    if @digits.length == 11 && @digits[0] == '1'
      @digits[0] =''
      @digits
    elsif @digits.length == 11 or @digits.length == 9
      "0000000000"
    else
      @digits
    end
  end

  def area_code
    @digits[0..2]
  end

  def to_s 
    pretty_print_number
  end

  private

  def pretty_print_number
    num = number
    "(" + "#{num[0..2]}" + ") " + "#{num[3..5]}" + "-" + "#{num[6..9]}"
  end

end
