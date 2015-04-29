# A nucleotide strand's unreversed complementary strand
class Complement
  RNA_COMPLEMENT_OF_DNA = { 'A' => 'U', 'C' => 'G', 'G' => 'C', 'T' => 'A' }
  DNA_COMPLEMENT_OF_RNA = RNA_COMPLEMENT_OF_DNA.invert

  def self.of_dna(strand)
    make_complement(strand, RNA_COMPLEMENT_OF_DNA)
  end

  def self.of_rna(strand)
    make_complement(strand, DNA_COMPLEMENT_OF_RNA)
  end

  def self.make_complement(strand, mapping)
    strand.chars.map do |base|
      mapping[base]
    end.join('')
  end
end
