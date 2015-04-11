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
    binary.split(//).reverse.each_with_index do |digit, power|
      if digit =~ /\D/
        return 0
      else
        decimal += digit.to_i * 2**power 
      end
    end
    decimal
  end
end
