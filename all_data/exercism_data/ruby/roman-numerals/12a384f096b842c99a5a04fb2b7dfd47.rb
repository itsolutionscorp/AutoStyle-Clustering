class Fixnum
  ROMAN_NUMERALS = ["I","V","X","L","C","D","M"]

  def to_roman
    self.to_s.chars.map(&:to_i).reverse
    .each_with_index.map do |item, index|
      index < 3 ? numerals = ROMAN_NUMERALS[((index*2)..(index*2 + 2))] : numerals = ROMAN_NUMERALS[6]
      if item > 0 && item < 4
        numerals[0] * item
      elsif item == 4
        numerals[0] + numerals[1]
      elsif item == 5
        numerals[1]
      elsif item > 5 && item < 9
        numerals[1] + (numerals[0] * (item % 5))
      elsif item == 9
        numerals[0] + numerals[2]
      end
    end.reverse.join
  end
end
