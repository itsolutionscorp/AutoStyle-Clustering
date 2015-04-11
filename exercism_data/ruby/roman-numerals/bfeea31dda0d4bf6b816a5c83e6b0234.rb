class Fixnum
  def to_roman
    roman_key.reduce(['', self]) do |(numerals, remainder), (numeral, value)|
      [numerals + numeral * (remainder / value), remainder % value]
    end.first
  end

private

  def roman_digits
    %w(I V X L C D M v x l c d m) # v = 5000, x = 10_000, l = 50_000, etc.
  end

  def roman_key
    roman_triples.reduce([[['I', 1]], 1]) do |(key, order), triple|
      [key + roman_pairs(*triple, order), order * 10]
    end.first.reverse
  end

  def roman_pairs(one, five, ten, order)
    [one + five, five, one + ten, ten].zip([4, 5, 9, 10].map { |n| n * order})
  end

  def roman_triples
    roman_digits.each_cons(3).select.each_with_index { |_, i| i.even? }
  end
end
