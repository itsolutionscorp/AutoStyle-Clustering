class Complement
  @@rna_dict = {
    'C' => 'G',
    'G' => 'C',
    'A' => 'T',
    'U' => 'A'
  }

  def self.of_dna(rna)
    strand_check(rna, @@rna_dict.invert)
  end

  def self.of_rna(dna)
    strand_check(dna, @@rna_dict)
  end

  private
  def self.strand_check(strand, dict)
    compare_strand = ''
    strand.each_char do |compare|
      compare_strand << dict[compare]
    end

    compare_strand
  end
end
