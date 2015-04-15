class Hamming
    def self.compute(strandA,strandB)
        strandA.chars.each_with_index.count do |char,index|
            count += 1 if strandB[index] && char!=strandB[index]
        end
    end
end
