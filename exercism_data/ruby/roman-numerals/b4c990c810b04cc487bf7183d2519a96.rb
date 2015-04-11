class Fixnum

    def to_roman
        romanNumerals = {
            1000 => 'M', 900 => 'CM', 500 => 'D', 400 => 'CD',
            100 => 'C', 90 => 'XC', 50 => 'L', 40 => 'XL',
            10 => 'X', 9 => 'IX', 5 => 'V', 4 => 'IV', 1 => 'I'
        }

        workNumber = self
        workArray = []
        outString = ''
        romanNumerals.each do |key, value|
            workArray = workNumber.divmod(key)
            workNumber = workArray[1]
            outString << value * workArray[0]
        end
        outString
    end

end
