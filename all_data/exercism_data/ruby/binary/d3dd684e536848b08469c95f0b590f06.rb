class Binary
  attr_reader :number

  def initialize(number)
    @number = number
  end

  def to_decimal
    check_if_binary

    decimal = @binary.each_with_index.reduce(0) do |sum, (value, index)|
      sum + value.to_i * (2 ** index)
    end
  end

  private

  def check_if_binary
    return @binary = [] if @number.match(/[a-zA-Z2-9]/)

    @binary = @number.chars
                     .map { |d| d.to_i}
                     .reverse
  end
end
