class Phone
  def initialize number
    @number = number
  end

  def number
    temp = @number.scan(/\d+/).join("")
    temp = temp[1..-1] if temp[0] == "1" && temp.size == 11
    return "0000000000" unless temp.size == 10
    temp
  end

  def area_code
    number[0..2]
  end

  def to_s
    "(#{number[0..2]}) #{number[3..5]}-#{number[6..-1]}"
  end
end
