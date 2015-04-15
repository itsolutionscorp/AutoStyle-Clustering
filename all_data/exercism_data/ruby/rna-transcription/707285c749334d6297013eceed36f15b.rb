class Complement
  DNA_TO_RNA = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(dna)
    self.transcribe_strand(dna, :dna)
  end

  def self.of_rna(rna)
    self.transcribe_strand(rna, :rna)
  end

private
  def self.transcribe_strand(strand_to_convert, from_this_base)
    strand_to_convert.each_char.inject('') do |t, nucleotide|
      t << self.find_nucleotide_complement_from(nucleotide, from_this_base)
    end
  end

  def self.find_nucleotide_complement_from(nucleotide, type)
    return type == :dna ? DNA_TO_RNA[nucleotide] : RNA_TO_DNA[nucleotide]
  end

end
