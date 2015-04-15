class Binary
  attr_reader :binary

  def initialize(binary)
    @binary = binary
  end

  def to_decimal
    if binary?
      reverse_binary_array.each_with_index.inject(0) do |sum, (value, index)| 
        sum += value*(2**index)
      end
    else
      0
    end
  end

  private

  def binary?
    binary.match(/^[0|1]+$/)
  end

  def reverse_binary_array
    binary.chars.reverse.map(&:to_i)
  end
end
