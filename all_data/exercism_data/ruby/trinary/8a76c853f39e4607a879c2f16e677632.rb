class Trinary

  def initialize(trinary)
    @trinary = trinary
  end

  def to_decimal
    @trinary.chars.reverse_each.with_index
      .reduce(0) {|sum, (char, power)| sum + char.to_i * (3**power)}
  end

end
