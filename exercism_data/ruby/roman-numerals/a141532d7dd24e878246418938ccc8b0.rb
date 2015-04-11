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
      base_index = 10 ** index # returns 1, 10, 100, 1000

      if num == 4 || num == 9
        four_nine_index = base_index * num # returns 4, 9, 40, 90, 400, 900
        to_roman = NUMBERAlS[four_nine_index] + to_roman
      else
        if num < 5
          base_index_total = NUMBERAlS[base_index] * num
          to_roman  = base_index_total + to_roman
        else
          base_index_total = NUMBERAlS[base_index] * (num - 5)

          five_index = NUMBERAlS[base_index * 5] # returns RNs for 5, 50, 500
          to_roman = five_index + base_index_total + to_roman 
        end
      end
    end

    to_roman
  end
end
