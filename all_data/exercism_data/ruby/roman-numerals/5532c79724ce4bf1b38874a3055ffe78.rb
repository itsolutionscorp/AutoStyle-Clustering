class Fixnum
  def to_roman
    output = roman_recurse(self, output='')
  end

  private

  def roman_recurse(n, output='')

    if n == 0
      output.reverse!
      output = output.sub 'DCCCC', 'CM'
      output = output.sub 'CCCC', 'CD'
      output = output.sub 'LXXXX', 'XC'
      output = output.sub 'XXXX', 'XL'
      output = output.sub 'VIIII', 'IX'
      output = output.sub 'IIII', 'IV'
      return output
    end

    if n % 1000  == 0
      output << 'M'
      n -= 1000

    elsif n % 500 == 0
      output << 'D'
      n -= 500

    elsif n % 100 == 0
      output << 'C'
      n -= 100

    elsif n % 50 == 0
      output << 'L'
      n -= 50

    elsif n % 10 == 0
      output << 'X'
      n -= 10

    elsif n % 5 == 0
      output << 'V'
      n -= 5

    elsif n % 1 == 0
      output << 'I'
      n -= 1

    end
    roman_recurse(n, output)
  end

end
