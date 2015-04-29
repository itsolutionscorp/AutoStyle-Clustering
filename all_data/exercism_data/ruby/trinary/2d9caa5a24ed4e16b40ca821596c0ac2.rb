class Trinary
  def initialize(trinary)
    @trinary = trinary =~ /^[012]+$/ ? trinary : 0
  end

  def to_decimal
    @decimal ||= @trinary.to_i(3)
  end
end
