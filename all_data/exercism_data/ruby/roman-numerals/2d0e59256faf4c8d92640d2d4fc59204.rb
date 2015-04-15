class Fixnum

  def to_roman
    roman = ''

    roman << char_n_times('M', self / 1000)
    rest = self % 1000

    if rest / 100 == 9
      roman << 'CM'
      rest -= 900
    end

    roman << char_n_times('D', rest / 500)
    rest = rest % 500

    if rest / 100 == 4
      roman << 'CD'
      rest -= 400
    end

    roman << char_n_times('C', rest / 100)
    rest = rest % 100

    if rest / 10 == 9
      roman << 'XC'
      rest -= 90
    end

    roman << char_n_times('L', rest / 50)
    rest = rest % 50

    if rest / 10 == 4
      roman << 'XL'
      rest -= 40
    end

    roman << char_n_times('X', rest / 10)
    rest = rest % 10

    case rest
    when 9 then
      roman << 'IX'
    when 4 then
      roman << 'IV'
    else
      roman << char_n_times('V', rest / 5)
      roman << char_n_times('I', rest % 5)
    end

    roman
  end

private

  def char_n_times(char, number)
    ''.ljust(number, char)
  end

end
