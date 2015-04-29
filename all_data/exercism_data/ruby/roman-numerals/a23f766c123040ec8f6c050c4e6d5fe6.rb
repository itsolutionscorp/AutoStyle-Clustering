class Numeric

  def to_roman
    RomanNumber.new(self).to_s
  end

end

class RomanNumber

  NUMERALS = {
       1 => 'I',
       5 => 'V',
      10 => 'X',
      50 => 'L',
     100 => 'C',
     500 => 'D',
    1000 => 'M',
  }

  def get_numeral(key)
    NUMERALS.fetch(key)
  end

  def initialize(val)
    unless val.between?(1, 3999)
      raise ArgumentError.new("%s must be less than 4000" % val)
    end

    @val = val
  end

  def to_s
    return get_numeral(@val) if NUMERALS.has_key?(@val)

    out = []
    div = @val

    (0..3).each do |pow|
      div, mod = div.divmod(10)
      next if mod == 0 & div < 9

      out << make_numeral(mod, 10 ** pow)
      break if div == 0
    end

    out.reverse.join('')
  end


  def make_numeral(num, mag=nil)
    str = ''

    case
    when num == 4 || num == 9
      str << get_numeral(mag)
      str << get_numeral(mag * (num + 1))
    else
      str << get_numeral(mag * 5) if 5 <= num
      num.modulo(5).times { str << get_numeral(mag) }
    end

    str
  end

end
