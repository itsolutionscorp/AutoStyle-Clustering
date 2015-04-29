class Hamming
    def self.compute(strandA,strandB)
        count=0
        strandA.chars.each_with_index do |char,index|
            count += 1 if strandB[index] && char!=strandB[index]
        end
        count
    end
end
