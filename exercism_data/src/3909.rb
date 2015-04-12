def compute(strandA,strandB)
        count=0
        strandA.chars.each_with_index do |char,index|
            count += 1 if strandB[index] and char!=strandB[index]
        end
        count
    end