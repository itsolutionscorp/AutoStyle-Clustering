module Roman

  ROMAN_NUMERALS = {
    1 => 'I',
    5 => 'V',
    10 => 'X',
    50 => 'L',
    100 => 'C',
    500 => 'D',
    1000 => 'M'
  }

LETTERS = [ 'M', 'D', 'C', 'L', 'X', 'V', 'I' ] # I could get this by numerals#values

  def numerals
    Hash[ROMAN_NUMERALS.to_a.reverse]
  end

  def to_roman
    num = self
    numerals.each_with_object('') do |(dec, rom), text|
      times, num = num.divmod dec
      if times == 4 # only 4s and 9s
        if text[-1].eql? LETTERS[ LETTERS.index( rom ) - 1 ] # checks if this is a 4 or a 9
          text.chop! << special(9, rom) # chops off the ROMAN FIVE and appends the ROMAN NINE
        else
          text << special(4, rom)
        end
      else
        text << rom * times
      end
    end
  end

  def special(num, rom)
    current_index = LETTERS.index( rom )
    LETTERS[ current_index ] + ( num == 4 ? LETTERS[ current_index - 1 ] : LETTERS[ current_index - 2 ] )
  end

end

class Fixnum
  include Roman
end
