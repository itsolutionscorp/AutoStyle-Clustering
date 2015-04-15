ROMAN_NUMERALS_MAPPING = {  M:1000, CM:900, D:500, CD:400, 
                            C:100, XC:90, L:50, XL:40, 
                            X:10, IX:9, V:5, IV:4, I:1 }

class Fixnum
    def to_roman
        n = self
        ROMAN_NUMERALS_MAPPING
        .each_with_object("") do |(numeral, value), roman|
            if n >= value
                roman << numeral.to_s * (n / value) 
                n %= value
            end
        end
    end
end
