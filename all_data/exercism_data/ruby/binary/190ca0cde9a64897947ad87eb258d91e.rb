class Binary 

  attr_reader :binary_number, :decimal_number
  def initialize(binary_number)
    @binary_number = binary_number
  end

  def to_decimal
    @decimal_number ||= convert_to_decimal
  end

  private
    def convert_to_decimal
      return 0 unless binary_number =~ /^[0-1]+$/
      binary_number.chars.reverse.each.with_index.inject(0) do |decimal_num, (bin_digit, index)|
        decimal_num + (bin_digit.to_i * (2**index))
      end
    end
end
