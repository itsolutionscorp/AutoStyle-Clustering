class Integer

  ROMAN_NUMERALS = {
       1 => 'I',
       5 => 'V',
      10 => 'X',
      50 => 'L',
     100 => 'C',
     500 => 'D',
    1000 => 'M',
  }

  def to_roman

    unless self.between?(1, 3999)
      raise ArgumentError.new("%s must be less than 4000" % self)
    end

    return ROMAN_NUMERALS.fetch(self) if ROMAN_NUMERALS.has_key?(self)

    out = []
    div = self

    (0..3).each do |pow|
      div, mod = div.divmod(10)
      next if mod == 0 & div < 9
      str = ''
      mag = 10 ** pow

      case
      when mod == 4 || mod == 9
        str << ROMAN_NUMERALS.fetch(mag)
        str << ROMAN_NUMERALS.fetch(mag * (mod + 1))
      else
        str << ROMAN_NUMERALS.fetch(mag * 5) if 5 <= mod
        mod.modulo(5).times { str << ROMAN_NUMERALS.fetch(mag) }
      end

      out << str
      break if div == 0
    end

    out.reverse.join('')
  end

end
