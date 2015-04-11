class Binary
  attr_accessor :binary
  def initialize(binary)
    @binary = binary.split("").map { |x| x.to_i }
  end

  def to_decimal
    total = 0
    binary.each_with_index do |num, index|
      if index == 0 && num == 0
        return 0
      end
      total += num * (2 ** (binary.length - index - 1))
    end
    total
  end
end
