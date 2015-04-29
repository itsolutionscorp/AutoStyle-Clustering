ROMAN = {1 => 'I', 4 => 'IV', 5 => 'V', 9 => 'IX', 10 => 'X', 40 => 'XL', 50 => 'L',
         90 => 'XC', 100 => 'C', 400 => 'CD', 500 => 'D', 900 => 'CM', 1000 => 'M'}

class Fixnum

  def to_roman
    rn=''
    num = self
    ROMAN.sort.reverse.each do |x, y|
      if num == 0
      elsif x % num == x
        times = num/x
        rn << ROMAN[x] * times
        newnum = x * times
        num = num % newnum
      elsif x == num
          rn << ROMAN[x]
          break
      end
    end
    rn
   end
end
