class Binary
  def initialize(bin)
    @binary_num = bin
  end

  def to_decimal
    return 0 unless @binary_num.match(/[^01]/).nil?

    # Split lines for readability
    @binary_num.each_char
      .with_index
      .inject([]) { |a, (e, i)| a << (e.to_i * (2**(@binary_num.length - i - 1))) }
      .reduce(:+)
  end
end
