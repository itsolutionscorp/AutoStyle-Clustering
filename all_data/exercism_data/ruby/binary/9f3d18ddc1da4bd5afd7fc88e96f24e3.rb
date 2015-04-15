class Binary
  attr_reader :number
  def initialize(number)
    @number = number
  end

  def to_decimal
    return 0 if @number.to_i == 0
    decimal = 0
    @number.split(//).reverse.each_with_index do |n, i|
      decimal += n.to_i * 2 ** i
    end
    decimal
  end
end
