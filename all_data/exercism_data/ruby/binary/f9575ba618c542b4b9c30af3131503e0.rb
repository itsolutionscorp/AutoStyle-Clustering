class Binary
  def initialize binary_num
    @binary = binary_num
  end

  def to_decimal
    @decimal ||= convert @binary
  end

  private

  def convert binary
    decimal = 0
    binary.chars.reverse.each_with_index do |digit, power|
      return 0 if digit =~ /\D/
      decimal += digit.to_i * 2**power 
    end
    decimal
  end
end
