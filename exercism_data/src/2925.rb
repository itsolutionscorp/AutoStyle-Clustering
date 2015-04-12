class Hamming
      def compute dna1, dna2
        (0..(dna1.size-1)).to_a.reduce(0) do |sum, index|
          sum = sum +1 unless dna1[index] == dna2[index]
          sum
        end
      end
end
