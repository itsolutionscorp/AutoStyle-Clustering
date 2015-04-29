class Binary
  def initialize(b_str)
    @b_str = b_str
  end

  def to_decimal
    return 0 unless @b_str =~ /\A\d+\z/ # only allow numbers in string

    results = []
    binary_exp = @b_str.length - 1 #set exponent start

    @b_str.each_char do |letter|
      results << letter.to_i * 2 ** binary_exp
      binary_exp -= 1
    end

    results.reduce(:+)
  end

end
