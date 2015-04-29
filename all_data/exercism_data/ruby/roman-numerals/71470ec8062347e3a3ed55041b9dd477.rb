class Fixnum
  # Maximum number can represent with these roman
  # numerical letters
  # MMMVCCCLXXXVIII
  MAX_ROMAN = 3888
  ROMAN_MAP = {
    1 => 'I',
    5 => 'V',
    10 => 'X',
    50 => 'L',
    100 => 'C',
    500 => 'D',
    1000 => 'M'
  }

  def to_roman
    raise if self > 3888
    idx = 1
    self.to_s.reverse.each_char.reduce('') do |roman, unity|
      unity_int = unity.to_i
      unity_str = case unity_int
                  when 0
                    ''
                  when 1..3
                    ROMAN_MAP[idx] * unity_int
                  when 4
                    ROMAN_MAP[idx] + ROMAN_MAP[idx * 5]
                  when 5
                    ROMAN_MAP[idx * 5]
                  when 6..8
                    ROMAN_MAP[idx * 5] + ROMAN_MAP[idx] * (unity_int - 5)
                  when 9
                    ROMAN_MAP[idx] + ROMAN_MAP[idx * 10]
                  else
                    raise 'Uh, this should not happen'
                  end
      idx *= 10
      unity_str + roman
    end
  end
end
