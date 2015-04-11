class Binary

  def initialize(binary)
    @binary = binary
  end

  def to_decimal
    return 0 if @binary =~ /[^01]/

    @binary.each_char.reverse_each.with_index
      .reduce(0) {|sum, (character, power)| sum + character.to_i * (2**power)}
  end

end
