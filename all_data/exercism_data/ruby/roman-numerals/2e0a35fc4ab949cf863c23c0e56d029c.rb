class Fixnum
  def to_roman
    if self == 900
        'CM'
    elsif self == 500
        'D'
    elsif self == 400
        'CD'
    elsif self == 90
        'XC'
    elsif self == 40
        'XL'
    else

        a = 'M'*(self/1000)

        if self%1000 > 900
            b = 'CM'
        else
            b = ''
        end

        c = 'D'*(self%900/500)

        if self > 400 && self%400 > 0 && self%400 < 100
            d = 'CD'
        else
            d = ''
        end

        if self%500 < 100 || self%400 < 100 || self%1000 > 900
            e = ''
        else
            e = 'C'*(self%400/100)
        end

        if self%100 > 90
            f = 'XC'
        else
            f = ''
        end

        if self%100 < 90
            g = 'L'*(self%100/50)
        else
            g = ''
        end

        if self%50 > 40 && self%100 < 90
            h = 'XL'
        else
            h = ''
        end

        if self >= 50 && ((self%100 < 90 && self%100 >= 50) || (self%100 < 40))
            i = 'X'*(self%50/10)
        elsif self > 40 && self < 50
            i = 'X'*(self%40/10)
        elsif self < 40
            i = 'X'*(self/10)
        else
            i = ''
        end

        if self%10 == 9
            j = 'IX'
        elsif self%10 != 9
            j = 'V'*(self%10/5)
        else
            j = ''
        end

        if self%5 == 4 && self%10 != 9
            k = 'IV'
        elsif self%5 < 4 && self%5 > 0
            k = 'I'*(self%5)
        else
            k = ''
        end

        a + b + c + d + e + f + g + h + i + j + k
    end
  end
end
