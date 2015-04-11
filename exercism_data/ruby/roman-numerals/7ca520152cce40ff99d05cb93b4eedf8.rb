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

      numberals = 10 ** index * num
      if NUMBERAlS[numberals]
        to_roman = NUMBERAlS[numberals] + to_roman
      elsif index === 0
        if num > 5
          leftover = num - 5
          to_roman += 'V' + ('I' * leftover)
        else
          to_roman += 'I' * num
        end
      elsif index == 1
        if num < 5
          to_roman = ('X' * num) + to_roman
        else
          to_roman = 'L' + ('X' * (num - 5)) + to_roman
        end
      elsif index == 2
        if num < 5
          to_roman = ('C' * num) + to_roman
        else
          to_roman = 'D' + ('C' * (num - 5)) + to_roman
        end
      elsif index == 3
        to_roman = ('M' * num) + to_roman
      end
    end

    to_roman
  end
end
