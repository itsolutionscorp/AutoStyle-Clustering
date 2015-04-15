class Binary

  def initialize(binary)
    @binary = binary
  end

  def to_decimal
    if self.valid_binary?
      decimal = 0
      (@binary.length - 1).downto(0) do |exp|
        if @binary[@binary.length - 1 - exp] == "1"
          decimal += (2 ** exp)
        end
      end

      decimal
    else
      return 0
    end
  end

  def valid_binary?
    one_zero_count = @binary.count("1") + @binary.count("0")
    if one_zero_count == @binary.length
      return true
    else
      return false
    end
  end
end
