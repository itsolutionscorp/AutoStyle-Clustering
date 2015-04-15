class Hamming
    def Hamming.compute(dna1,dna2)
        dna1.each_char.zip(dna2.each_char).count do |arr|
            not arr[1].nil? and arr[1]!=arr[0]
        end
    end
end
