class Hamming
    def self.compute(a,b)
        count=0
        a.split(//).each_with_index do |c,i|
            count += 1 if b[i] and c!=b[i]
        end
        count
    end
end
