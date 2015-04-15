class Binary < String
  ASCII_ZERO = '0'.ord

  def to_decimal
    max_i = length - 1
    each_char.with_index.reduce(0) do |base10,(char,i)|
      return 0 unless valid? char
      base10 + ( (char.ord - ASCII_ZERO) * 2**(i - max_i).abs )
    end
  end

  private

  def valid? char
    char == '1' || char == '0'
  end
end
