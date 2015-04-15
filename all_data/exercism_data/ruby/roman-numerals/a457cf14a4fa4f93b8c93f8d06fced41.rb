class Fixnum

    def to_roman
        romanNumerals = {
            1000 => 'M', 900 => 'CM', 500 => 'D', 400 => 'CD',
            100 => 'C', 90 => 'XC', 50 => 'L', 40 => 'XL',
            10 => 'X', 9 => 'IX', 5 => 'V', 4 => 'IV', 1 => 'I'
        }

        remainder = self
        romanNum = ''
        romanNumerals.each do |arabic, roman|
            # I'm an idiot. This does the same thing as #divmod
            # but without the unnecessary and confusing divModArray
            romanNum << roman * (remainder / arabic)
            remainder = remainder % arabic
        end
        romanNum
    end

end
