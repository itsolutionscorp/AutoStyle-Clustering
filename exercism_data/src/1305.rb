class Hamming
    def compute(s,t)
        distance = 0
        (0..s.length).each do |index|
            distance += 1 if s[index] != t[index] 
        end
        distance
    end
end
