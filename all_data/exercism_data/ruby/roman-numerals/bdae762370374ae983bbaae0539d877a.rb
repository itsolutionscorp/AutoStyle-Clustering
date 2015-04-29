module Roman

  def self.romanize(num)
    num.to_s.chars.reverse.map.with_index{ |digit, place|
      romanize_digit(digit.to_i, place)
    }.reverse.join
  end

  private
  NUMERALS = [%w{I V X}, %w{X L C}, %w{C D M}, %w{M M M}]

  def self.romanize_digit(digit, place_index)
    set = NUMERALS[place_index]
    return set[0] + set[2] if digit == 9
    return set[0] + set[1] if digit == 4
    val = ''
    if digit >= 5
      val << set[1]
      digit -= 5
    end
    val << (set[0] * digit)
  end
end

class Fixnum
  def to_roman
    Roman.romanize(self)
  end
end
