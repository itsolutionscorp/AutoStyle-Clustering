class Hamming
    def compute(string1, string2)
        raise "Input strings of unequal length." if string1.length != string2.length

        result = 0
        string1.chars.zip(string2.chars).each do |char1, char2|
            result += 1 if char1 != char2
        end
        result
    end
end
