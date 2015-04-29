class Fixnum
    def to_roman
        matrix = [
            ["M", 1000],  ["CM", 900], ["D", 500], ["CD", 400],
            ["C", 100],   ["XC", 90],  ["L", 50],  ["XL", 40], 
            ["X", 10],    ["IX", 9],   ["V", 5],   ["IV", 4], 
            ["I", 1]
        ]

        value = self

        matrix.reduce('') do |base, curr|
            count, value = value.divmod(curr.last)
            base << (curr.first * count)
        end
    end
end

p 10.to_roman
