class Binary
  def initialize(number)
    @number = number.to_s
  end

  def to_decimal
    return 0 if @number.match(/a-z/)
    reversed_numbers = @number.reverse.chars.map(&:to_i)
    sum  = 0
    reversed_numbers.each_with_index do |number, index|
      sum += number * (2 ** index)
    end
    sum
  end
end
