class Fixnum
  def to_roman
    
    digits = self.to_s.reverse.split('').map{ |i| i.to_i }
    roman = ''

    if digits[3]
      roman <<  case digits[3]
                  when 1 then 'M'
                  when 2 then 'MM'
                  when 3 then 'MMM'
                  else ''
                end
    end

    if digits[2]
      roman <<  case digits[2]
                  when 1 then 'C'
                  when 2 then 'CC'
                  when 3 then 'CCC'
                  when 4 then 'CD'
                  when 5 then 'D'
                  when 6 then 'DC'
                  when 7 then 'DCC'
                  when 8 then 'DCCC'
                  when 9 then 'CM' 
                  else ''
                end
    end     

    if digits[1]
      roman <<  case digits[1]
                  when 1 then 'X'
                  when 2 then 'XX'
                  when 3 then 'XXX'
                  when 4 then 'XL'
                  when 5 then 'L'
                  when 6 then 'LX'
                  when 7 then 'LXX'
                  when 8 then 'LXXX'
                  when 9 then 'XC'
                  else ''
                end
    end

    if digits[0]
      roman <<  case digits[0]
                  when 1 then 'I'
                  when 2 then 'II'
                  when 3 then 'III'
                  when 4 then 'IV'
                  when 5 then 'V'
                  when 6 then 'VI'
                  when 7 then 'VII'
                  when 8 then 'VIII'
                  when 9 then 'IX'
                  else ''
                end
    end

    roman
  end
end
