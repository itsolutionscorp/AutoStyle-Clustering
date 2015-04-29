class Binary

  def initialize bin
    @bin = bin
  end

  def to_decimal
    return 0 if @bin.match /[^01]/

    @bin.reverse.each_char.with_index.inject(0) do |sum,(n,i)|
      sum + 2**i * n.to_i
    end
  end
end
