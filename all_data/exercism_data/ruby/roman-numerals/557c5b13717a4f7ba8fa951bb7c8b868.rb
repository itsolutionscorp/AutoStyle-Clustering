class Fixnum
  def to_roman
    total = self
    romanized = []
    while total > 0 do
      if total >= 1000
        romanized << 'M'
        total = total - 1000
      elsif total >= 900 && total < 1000
        romanized << 'CM'
        total = total - 900
      elsif total >= 500
        romanized << 'D'
        total = total - 500
      elsif total >= 400 && total < 500
        romanized << 'CD'
        total = total - 400
      elsif total >= 100
        romanized << 'C'
        total = total - 100
      elsif total >= 90 && total < 100
        romanized << 'XC'
        total = total - 90
      elsif total >= 50
        romanized << 'L'
        total = total - 50
      elsif total >= 40 && total < 50
        romanized << 'XL'
        total = total - 40
      elsif total >= 10
        romanized << 'X'
        total = total - 10
      elsif total == 9
        romanized << 'IX'
        total = total - 9
      elsif total >= 5
        romanized << 'V'
        total = total - 5
      elsif total == 4
        romanized  << 'IV'
        total = total - 4
      else
        romanized << 'I'
        total = total - 1
      end
    end
    romanized.join
  end
end
