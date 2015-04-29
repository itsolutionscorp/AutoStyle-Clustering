class Complement
  DNA_TO_RNA_COMPLEMENTS = { 'G' => 'C',
                             'C' => 'G',
                             'T' => 'A',
                             'A' => 'U'
  }

 class << self
   def of_dna(sequence)
     sequence.split('').map do |nucleotide|
       raise ArgumentError if nucleotide == 'U'
       DNA_TO_RNA_COMPLEMENTS[nucleotide]
     end.join('')
   end

   def of_rna(sequence)
     sequence.split('').map do |nucleotide|
       raise ArgumentError if nucleotide == 'T'
       DNA_TO_RNA_COMPLEMENTS.invert[nucleotide]
     end.join('')
   end
 end
end
