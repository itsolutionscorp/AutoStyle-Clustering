class PhoneNumber
  def initialize(num)
    @num = num
  end

  def number
    @num.gsub!(/[^0-9]/, '')
    if @num.length == 11 && @num[0] == "1"
      @num[0] = ""
    elsif @num.length >= 11 || @num.length < 10
      @num = "0000000000"
    end
    return @num
  end

  def area_code
    @num[0,3]
  end

  def to_s
    number
    "(#{@num[0,3]}) #{@num[3,3]}-#{@num[6,4]}"
  end
end
