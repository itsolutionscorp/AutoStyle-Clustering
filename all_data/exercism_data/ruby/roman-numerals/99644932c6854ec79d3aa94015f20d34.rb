class Fixnum

  ROMAN_HASH = { 1 => "I", 5 => "V", 10 => "X", 50 => "L", 100 => "C", 500 => "D", 1000 => "M"}

  def to_roman
    result = []

    self.to_s.chars.reverse_each.with_index do |number, index|
      number = number.to_i
      (div, mod) = number.divmod(5)
      local = 10 ** index

      if mod === 4
        result << ROMAN_HASH[local * 10] if div === 1
        result << ROMAN_HASH[local * 5] if div === 0
        result << ROMAN_HASH[local]
      elsif div === 0
        result << ROMAN_HASH[local] * number
      elsif div === 1
        result.concat([ROMAN_HASH[local] * mod, ROMAN_HASH[local * 5]])
      end
    end
    result.reverse.join("")
  end
end
