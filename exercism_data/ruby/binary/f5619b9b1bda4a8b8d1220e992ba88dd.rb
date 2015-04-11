class Binary
  def initialize(binary)
    @binary = binary
  end

  def to_decimal
    decimal = 0
    if @binary.to_i.to_s.length != binaries.length
      decimal
    else
      binaries.each_with_index do |bin, index|
        decimal += bin*2**index
      end
      decimal
    end
  end

  def binaries
    bnums = @binary.split('')
    bnums.map! { |i| i.to_i }
    bnums.reverse!
  end
end
