class Fixnum
  NUMBERAlS = { 1=> 'I', 4 => 'IV',  5 => 'V', 
                9 => 'IX' , 10 => 'X' , 40 => 'XL', 
                50 => 'L' , 90 => 'XC' ,100 => 'C' ,
                400 => 'CD' ,500 => 'D' ,900 => 'CM' ,
                1000 => 'M' }
  def to_roman
    to_roman = ''
    
    self.to_s.reverse.chars.each_with_index do |num, index|
      num = num.to_i

      if index === 0
        if NUMBERAlS[num]
          to_roman += NUMBERAlS[num]
        elsif num > 5
          leftover = num - 5
          to_roman += 'V' + ('I' * leftover)
        else
          to_roman += 'I' * num
        end
      elsif index == 1
        numberals = num * 10
        if NUMBERAlS[numberals]
          to_roman = NUMBERAlS[numberals] + to_roman
        elsif num < 5
          to_roman = ('X' * num) + to_roman
        else
          to_roman = 'L' + ('X' * (num - 5)) + to_roman
        end
      elsif index == 2
        numberals = num * 100
        if NUMBERAlS[numberals]
          to_roman = NUMBERAlS[numberals] + to_roman
        elsif num < 5
          to_roman = ('C' * num) + to_roman
        else
          to_roman = 'D' + ('C' * (num - 5)) + to_roman
        end
      elsif index == 3
        numberals = num * 1000
        if NUMBERAlS[numberals]
          to_roman = NUMBERAlS[numberals] + to_roman
        else
          to_roman = ('M' * num) + to_roman
        end
      end
    end

    to_roman
  end
end
