module Complement
  DNA_TO_RNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.map_each_nucleotide(hash, string)
    result = ''
    string.each_char do |nucleotide|
      result << hash[nucleotide]
    end

    result
  end

  def self.of_dna(dna_nucleotide)
    map_each_nucleotide(DNA_TO_RNA, dna_nucleotide)
  end

  def self.of_rna(rna_nucleotide)
    map_each_nucleotide(DNA_TO_RNA.invert, rna_nucleotide)
  end
end
