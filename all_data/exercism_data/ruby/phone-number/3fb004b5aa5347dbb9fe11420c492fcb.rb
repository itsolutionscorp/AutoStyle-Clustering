class PhoneNumber
  def initialize(str)
    @number = str.to_s.gsub(/[^a-zA-Z\d]/, "")
    @number = number[1..-1] if @number.length == 11 && @number.start_with?("1")
    @number = "0000000000"  if @number.length != 10 || @number.index(/[a-zA-Z]/)
  end

  def area_code
    @number[0..2]
  end

  def number
    @number
  end

  def to_s
    "(#{@number[0..2]}) #{@number[3..5]}-#{@number[6..9]}"
  end
end
