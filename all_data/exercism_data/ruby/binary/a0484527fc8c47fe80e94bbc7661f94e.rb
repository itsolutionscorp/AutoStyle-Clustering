class Binary

  def initialize(binary_string)
    @binary_array = valid_binary?(binary_string) ? convert_to_array(binary_string) : []
  end

  def to_decimal(binary_array = @binary_array, decimal = 0)
    if binary_array.length === 0
      return decimal
    else
      decimal = decimal + (binary_array.shift * (2**binary_array.length))
      to_decimal(binary_array, decimal)
    end
  end

  private

  def valid_binary?(binary_string)
    /\A[0-1]*\z/ === binary_string
  end

  def convert_to_array(binary_string)
    binary_string.split('').map{|num| num.to_i}
  end
end
