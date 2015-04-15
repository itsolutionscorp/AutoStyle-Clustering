class Binary
  def initialize(input)
    @input = input.reverse.split("")
  end

  def to_decimal
    return 0 if !@input.all? { |d| "01".include?(d) }
    @input.each_with_index.map { |d, i| d == "1" ? 2 ** i : 0 }.reduce(:+)
  end
end
