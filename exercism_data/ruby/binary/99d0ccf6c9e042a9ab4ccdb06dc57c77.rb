class Binary
  attr_reader :number, :result

  def initialize(number)
    @number = number
    @result = 0
  end

  def format_number(number)
    @number.split('')
  end

  def digits_to_i(number)
    number.map {|digit| digit.to_i}
  end

  def to_decimal
    number = format_number(number)
    number.each do |digit|
      return 0 if digit =~ /[A-Za-z]/
    end
    number = digits_to_i(number)
    number.each_with_index do |n, index|
      @result = (result * 2) + n
    end
    @result
  end
end
