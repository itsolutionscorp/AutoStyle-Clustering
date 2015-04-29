class Hamming
    def self.compute(arg1, arg2)
        chars1 = arg1.split("")
        chars2 = arg2.split("")
        len = chars1.length
        anw = 0
        for i in 0..len
            if chars1[i] != chars2[i] then
                anw += 1
            end
        end
        return anw
    end
end
