class Binary
  def initialize(n)
    @n = n
  end

  def to_decimal
    return 0 if @n.match(/\D/)
    @n.to_i(2)
  end
end
