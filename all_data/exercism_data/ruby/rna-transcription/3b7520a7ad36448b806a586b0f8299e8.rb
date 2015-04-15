class Complement
  DNA_TO_RNA_COMPLEMENTS = { 'G' => 'C',
                             'C' => 'G',
                             'T' => 'A',
                             'A' => 'U'
  }

  RNA_TO_DNA_COMPLEMENTS = DNA_TO_RNA_COMPLEMENTS.invert

 class << self
   def of_dna(sequence)
     sequence.split('').map do |nucleotide|
       DNA_TO_RNA_COMPLEMENTS.fetch(nucleotide) { raise ArgumentError }
     end.join('')
   end

   def of_rna(sequence)
     sequence.split('').map do |nucleotide|
       RNA_TO_DNA_COMPLEMENTS.fetch(nucleotide) { raise ArgumentError }
     end.join('')
   end
 end
end
