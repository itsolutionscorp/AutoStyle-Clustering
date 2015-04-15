class Binary
  def initialize bin
    @bin = bin.match(/[^01]/) ? '0' : bin
  end

  def to_decimal
    @bin.each_char.inject(0) do |sum, n|
      sum *= 2 if sum > 0
      sum += n.to_i
    end
  end
end
