class Fixnum
  NUMBERAlS = { 1=> 'I', 4 => 'IV',  5 => 'V', 
                9 => 'IX' , 10 => 'X' , 40 => 'XL', 
                50 => 'L' , 90 => 'XC' ,100 => 'C' ,
                400 => 'CD' ,500 => 'D' ,900 => 'CM' ,
                1000 => 'M' }

  TENS = { 0 => 'I', 1 => 'X', 2 => 'C', 3 => 'M'}

  def to_roman
    to_roman = ''

    self.to_s.reverse.chars.each_with_index do |num, index|
      num = num.to_i
      numberals = 10 ** index * num

      if NUMBERAlS[numberals]
        to_roman = NUMBERAlS[numberals] + to_roman
      elsif num < 5
        to_roman = TENS[index] * num + to_roman
      elsif index === 0
        to_roman += 'V' + ('I' * (num- 5))
      elsif index == 1
        to_roman = 'L' + ('X' * (num - 5)) + to_roman
      elsif index == 2
        to_roman = 'D' + ('C' * (num - 5)) + to_roman
      end
    end

    to_roman
  end
end
