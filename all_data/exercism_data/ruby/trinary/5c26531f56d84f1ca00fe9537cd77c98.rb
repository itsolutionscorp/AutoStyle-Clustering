class Trinary
  def initialize(trin)
    @trinary_num = trin
  end

  def to_decimal
    return 0 unless @trinary_num.match(/[^012]/).nil?

    # Split lines for readability
    @trinary_num.each_char
      .with_index
      .inject([]) { |a, (e, i)| a << (e.to_i * (3**(@trinary_num.length - i - 1))) }
      .reduce(:+)
  end
end
