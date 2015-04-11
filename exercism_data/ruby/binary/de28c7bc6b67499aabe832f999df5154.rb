class Binary
  attr_reader :to_decimal

  def initialize(s)
    @to_decimal = s.to_i(2)
  end
end
