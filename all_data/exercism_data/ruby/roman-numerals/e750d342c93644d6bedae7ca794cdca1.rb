class Fixnum
  def to_roman
    result = ''
    string = self.to_s
    for i in 0..string.length - 1
      if string[i] != '0'
        numeral = to_numeral(string[i, string.length])
        result += numeral
      end
    end
    return result
  end
end

def to_numeral(number)
  number = number.to_i
  if number != 0
    if number >= 1000
      thousands = number / 1000
      return 'M' * thousands
    elsif number >= 100
      hundreds = number / 100
      return 'C' * hundreds unless hundreds > 3
      return 'CD' unless hundreds != 4
      return 'D' unless hundreds != 5
      return 'D' + 'C' * (hundreds - 5) unless hundreds > 8
      return 'CM'
    elsif number >= 10
      tens = number / 10
      return 'X' * tens unless tens > 3
      return 'XL' unless tens != 4
      return 'L' unless tens != 5
      return 'L' + 'X' * (tens - 5) unless tens > 8
      return 'XC'
    else
      return 'I' * number unless number > 3
      return 'IV' unless number != 4
      return 'V' unless number != 5
      return 'V' + 'I' * (number - 5) unless number > 8
      return 'IX'
    end
  end
end
