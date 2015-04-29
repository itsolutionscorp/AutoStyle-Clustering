class Fixnum
  def to_roman
    roman_key.reduce('I' * self) { |numerals, pair| numerals.gsub(*pair) }
  end

private

  def roman_digits
    %w(I V X L C D M v x l c d m) # v = 5000, x = 10_000, l = 50_000, etc.
  end

  def roman_key
    roman_triples.reduce([]) { |key, triple| key + roman_pairs(*triple) }
  end

  def roman_pairs(one, five, ten)
    [[       one    * 5,       five],
     [      five    * 2,        ten],
     [       one    * 4, one + five],
     [five + one + five, one +  ten]]
  end

  def roman_triples
    roman_digits.each_cons(3).select.each_with_index { |_, i| i.even? }
  end
end
