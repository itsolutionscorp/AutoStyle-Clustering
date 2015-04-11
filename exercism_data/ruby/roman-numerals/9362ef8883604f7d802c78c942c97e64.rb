class Fixnum

    CONVERTER = {
        1000 => 'M', 900 => 'CM', 500 => 'D', 400 => 'CD',
        100 => 'C', 90 => 'XC', 50 => 'L', 40 => 'XL',
        10 => 'X', 9 => 'IX', 5 => 'V', 4 => 'IV', 1 => 'I'
    }

    def to_roman
        
        i = self
        converted = ''
        
        CONVERTER.each do |arabic, roman|
            while i >= arabic
                converted << roman 
                i -= arabic
            end
        end
        
        converted
        
    end

end
