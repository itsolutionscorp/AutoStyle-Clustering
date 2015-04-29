class Hamming
  def self.compute dna_strand_a, dna_strand_b
    merged_dna = dna_strand_b.split('').zip dna_strand_b.split('')
    merged_dna.reduce(0) do |differences, joint|
      differences += 1 if joint.uniq == 2
      differences
    end
  end

end
