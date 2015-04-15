class Binary

  def initialize(binary)
    @binary = binary
  end

  def to_decimal
    if @binary =~ /[^01]/
      return 0
    end

    decimal = 0
    @binary.reverse.each_char.with_index do |character, index|
      decimal += character.to_i * (2**index)
    end
    decimal
  end

end
