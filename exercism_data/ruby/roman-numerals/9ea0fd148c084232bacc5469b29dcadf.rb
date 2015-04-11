module Roman
  def to_roman
    return nil if self > 3000

    digits = self.to_s.split('')

    ones  = ['I', 'X', 'C', 'M']
    fives = ['V', 'L', 'D']

    digits.reverse.map.with_index do |digit, i|
      n    = digit.to_i

      five = fives[i]
      one  = ones[i]
      ten  = ones[i+1]

      case n
      when 1..3
        one * n
      when 4
        one + five
      when 5
        five
      when 6..8
        five + one * (n - 5)
      when 9
        one + ten
      else
        # NOOP
      end
    end.reverse.join
  end
end

class Integer
  include Roman
end
