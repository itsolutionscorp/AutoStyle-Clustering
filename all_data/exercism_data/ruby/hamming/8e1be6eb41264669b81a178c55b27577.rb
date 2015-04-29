class Hamming
   def self.compute(strand_a, strand_b)
      distance = 0
      paired_nucleotides(strand_a, strand_b).each do |nucleotide_a, nucleotide_b|
         break if !nucleotide_a || !nucleotide_b
         distance += 1 if nucleotide_a != nucleotide_b
      end
      distance
   end

   private
   def self.paired_nucleotides(string_a, string_b)
      string_a.chars.zip(string_b.chars)
   end
end
