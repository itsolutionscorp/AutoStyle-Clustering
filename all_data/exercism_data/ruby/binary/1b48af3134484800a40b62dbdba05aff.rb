class Binary
  def initialize(bits)
  	@bits = parse_bits(bits)
  end

  def to_decimal
    @bits.inject(0) { |r, n| r * 2 + n }
  end

  private

  def parse_bits(bits)
  	bits.split('').map do |c|
  	  return [] unless '01'.include?(c)
  	  c.to_i
  	end
  end
end
