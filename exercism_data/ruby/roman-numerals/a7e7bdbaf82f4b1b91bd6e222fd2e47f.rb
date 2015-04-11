class Integer
    def to_roman
        copy = self
        roman_numeral = ''

        # thousands place
        roman_numeral += 'M' * (copy / 1_000)
        copy %= 1_000

        # hundreds place
        roman_numeral += case copy / 100
        when 9
            'CM'
        when 5..8
            'D' + 'C' * ((copy - 500) / 100)
        when 4
            'CD'
        else
            'C' * (copy / 100)
        end
        copy %= 100

        # tens place
        roman_numeral += case copy / 10
        when 9
            'XC'
        when 5..8
            'L' + 'X' * ((copy - 50) / 10)
        when 4
            'XL'
        else
            'X' * (copy / 10)
        end
        copy %= 10

        #ones place
        roman_numeral += case copy
        when 9
            'IX'
        when 5..8
            'V' + 'I' * (copy - 5)
        when 4
            'IV'
        else
            'I' * copy
        end

        roman_numeral
    end
end
