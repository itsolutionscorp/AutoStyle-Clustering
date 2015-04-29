class Fixnum
  ROMAN_HASH = {M: 1000, CM: 900, D: 500, CD: 400, C: 100, XC: 90, L: 50, XL: 40, X: 10, IX: 9, V: 5, IV: 4, I: 1}
  def to_roman
    number = self
    numeral = ''
    while number > 0
       k, v = cycle_through_hash_to_find_biggest_number number
       numeral += k
       number -= v
    end
    numeral
  end

  def cycle_through_hash_to_find_biggest_number n
    ROMAN_HASH.each do |k, v|
      if v <= n
        return [k.to_s, v]
      end
    end
  end
end
