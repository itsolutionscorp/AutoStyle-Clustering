class DNA
   @@validNucleotides = ['A', 'C', 'G', 'T', 'U']

   def initialize (sequence)
      @sequence = sequence
   end

   def is_nucleotide? (nucleotide)
      @@validNucleotides.index nucleotide
   end

   def count (nucleotide)
      if not is_nucleotide? nucleotide
         raise ArgumentError
      end

      @sequence.count nucleotide
   end

   def nucleotide_counts ()
      initialCount = {'A'=> 0, 'C'=> 0,'G'=> 0, 'T'=> 0}
      @sequence.chars.each_with_object(initialCount) do |nucleotide, count|
         count[nucleotide] += 1
      end
   end
end
