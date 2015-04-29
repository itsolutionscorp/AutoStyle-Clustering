class Trinary
  def initialize(trinary)
    @trinary = trinary
  end

  def to_decimal
    @decimal ||= @trinary =~ /[012]+/ ? @trinary.to_i(3) : 0
  end
end
