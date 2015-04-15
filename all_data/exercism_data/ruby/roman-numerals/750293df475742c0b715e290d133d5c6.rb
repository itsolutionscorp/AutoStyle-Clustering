module Roman
  CONVERSIONS = {
    1 => 'I',
    5 => 'V',
    10 => 'X',
    50 => 'L',
    100 => 'C',
    500 => 'D',
    1000 => 'M'
  }

  def to_roman
    snum = self.to_s.reverse
    power = 0
    romanized = ''
    snum.each_char do |c|
      u = c.to_i
      magnitude = 10 ** power
      if u < 5
        if u == 4
          romanized.prepend(CONVERSIONS[magnitude]+CONVERSIONS[magnitude*5])
        else
          romanized.prepend(CONVERSIONS[magnitude]*u)
        end
      elsif u > 5
        if u == 9
          romanized.prepend(CONVERSIONS[magnitude]+CONVERSIONS[magnitude*10])
        else
          romanized.prepend(CONVERSIONS[magnitude*5]+CONVERSIONS[magnitude]*(u-5))
        end
      else
        romanized.prepend(CONVERSIONS[magnitude*5])
      end
      power += 1
    end
    return romanized
  end
end

class Fixnum
  include Roman
end
