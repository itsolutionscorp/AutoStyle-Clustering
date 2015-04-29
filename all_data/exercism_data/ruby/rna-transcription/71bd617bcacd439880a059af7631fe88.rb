class Complement
  MAPPING_DNA2RNA = {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }

  MAPPING_RNA2DNA = {
      'G' => 'C',
      'C' => 'G',
      'A' => 'T',
      'U' => 'A'
    }

  def self.of_dna (dna)
    return self.of_strand(dna, :dna)
  end

  def self.of_rna (rna)
    return self.of_strand(rna, :rna)
  end

  def self.of_strand(strand, strand_type)
    if strand_type  == :dna
      mapping =  MAPPING_DNA2RNA
    else
      mapping = MAPPING_RNA2DNA
    end

    strand.length.times.each do |i|
      strand[i] = mapping[strand[i]]
    end

    return strand
  end
end
