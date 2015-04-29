class Trinary

	def initialize (var)
		@var = var.match(/[^0-9]/) ? '0' : var
	end

  def to_decimal
    @var.each_char.inject(0) { |sum, n| sum * 3 + n.to_i }
  end
end
