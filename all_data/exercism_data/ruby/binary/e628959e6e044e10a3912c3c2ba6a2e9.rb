class Binary

  def initialize(binary_digits)
    @binary_digits = binary_digits
  end

  def to_decimal
    decimal = 0

    @binary_digits.each_char.with_index do |char, idx|
      if !char.match(/[0-9]/)
        decimal = 0
        break
      end
      power = @binary_digits.length-1-idx
      decimal += char.to_i * 2**(power)
    end

    decimal
  end
end
