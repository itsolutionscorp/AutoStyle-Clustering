class Fixnum
  NUMBERAlS = { 1=> 'I',     4 => 'IV',  5 => 'V', 
                9 => 'IX',   10 => 'X',  40 => 'XL', 
                50 => 'L',   90 => 'XC', 100 => 'C',
                400 => 'CD', 500 => 'D', 900 => 'CM',
                1000 => 'M' }

  def to_roman
    to_roman = ''
    
    self.to_s.reverse.chars.each_with_index do |num, index|
      num = num.to_i

      if num == 4 || num == 9
        fours_nines = 10 ** index * num # returns 4, 9, 40, 90, 400, 900
        to_roman = NUMBERAlS[fours_nines] + to_roman
      else
        tens = 10 ** index # returns 1, 10, 100, 1000

        if num < 5
          to_roman = NUMBERAlS[tens] * num + to_roman
        else
          fives = 10 ** index * 5 # returns 5, 50, 500
          to_roman = NUMBERAlS[fives] + (NUMBERAlS[tens] * (num - 5)) + to_roman 
        end
      end
    end

    to_roman
  end
end
