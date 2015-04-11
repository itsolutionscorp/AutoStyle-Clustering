class Binary
  def initialize(binary)
    @binary = binary
  end

  def to_decimal
    binary_array = @binary.chars.to_a
    binary_length = binary_array.length - 1

    sum = 0
    0.upto(binary_length).each do |digit|
      binary = binary_array[binary_length - digit].to_i
      return 0 unless binary == 0 || binary == 1
      sum += 2**digit * binary
    end
    sum
  end
end
