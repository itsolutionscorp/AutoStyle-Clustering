class Fixnum
  ROMAN = %w(I V X L C D M)

  def to_roman
    roman = ''
    number = self
    pos = 0

    while number > 0
      digit = decr = number % 10

      case digit
      when 4, 9
        roman << ROMAN[pos * 2 + digit / 5 + 1]
        roman << ROMAN[pos * 2]
      when 1..3,6..8
        decr %= 5
        roman << ROMAN[pos * 2] * decr
      when 5
        roman << ROMAN[pos * 2 + 1]
      else
        pos += 1
        number /= 10
      end

      number -= decr
    end

    roman.reverse
  end
end
