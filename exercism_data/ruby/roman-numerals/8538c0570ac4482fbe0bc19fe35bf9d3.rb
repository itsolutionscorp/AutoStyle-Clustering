class Fixnum

    def to_roman
        romanNumerals = {
            1000 => 'M', 900 => 'CM', 500 => 'D', 400 => 'CD',
            100 => 'C', 90 => 'XC', 50 => 'L', 40 => 'XL',
            10 => 'X', 9 => 'IX', 5 => 'V', 4 => 'IV', 1 => 'I'
        }

        # Made variable names more descriptive.
        remainder = self
        divModArray = []
        romanNum = ''
        romanNumerals.each do |arabic, roman|
            divModArray = remainder.divmod(arabic)
            # Divmod returns an array of two numbers where
            # 21.divmod(5) #=> [4, 1]
            remainder = divModArray[1]
            romanNum << roman * divModArray[0]
        end
        romanNum
    end

end
