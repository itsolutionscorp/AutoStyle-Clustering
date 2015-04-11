class Fixnum
  NUMERALS = {
    1    => 'I',
    5    => 'V',
    10   => 'X',
    50   => 'L',
    100  => 'C',
    500  => 'D',
    1000 => 'M'
  }

  def to_roman
    num = self
    place = 1000
    res = ''
    while place > 0
      q, num = num.divmod(place)
      case
      when q < 4
        q.times { res << NUMERALS[place] }
      when q == 4
        res << NUMERALS[place] << NUMERALS[place * 5]
      when q == 9
        res << NUMERALS[place] << NUMERALS[place * 10]
      else
        res << NUMERALS[place * 5]
        (q - 5).times { res << NUMERALS[place] }
      end
      place /= 10
    end
    res
  end
end
