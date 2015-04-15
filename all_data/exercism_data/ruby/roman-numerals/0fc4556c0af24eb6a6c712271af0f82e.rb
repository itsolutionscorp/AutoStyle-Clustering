class Fixnum

  def to_roman
    total = [] 
    convert(self, total)
  end

private

  def convert(input, total)
    num = input
    r_numerals = {1000 => 'M', 500 => 'D', 100 => 'C', 50 => 'L', 10 => 'X', 5 => 'V', 1 => 'I'}
    
    r_numerals.each_key do |k|
      if k <= num
        total << r_numerals[k]
        
        # checks & adjusts for the special cases of 4, 9, 40, 90 etc
        if total.count(r_numerals[k]) == 4
          if total.length == 4 || r_numerals.key(total[-5]) / 10 == r_numerals.key(total[-4])
            total.pop(4)
            total.push(r_numerals[k], r_numerals [k * 5])   
          else
            total.pop(5)
            total.push(r_numerals[k], r_numerals[k * 10])  
          end
        end
        
        num -= k
        break
      end
    end

    convert(num, total) if num > 0
    total.join
  end
end
