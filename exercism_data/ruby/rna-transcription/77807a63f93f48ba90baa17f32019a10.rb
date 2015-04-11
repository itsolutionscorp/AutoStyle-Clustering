class Complement
  TODNA = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  TORNA = {'G' => 'C', 'C' => 'G', 'U' => 'A', 'A' => 'T'}
  def self.of_dna(strand)
    strand.split(//).each_with_index do |char, index|
      if TODNA.keys.include?(char)
        strand[index] = TODNA[char]
      end
    end
    strand
  end

  def self.of_rna(strand)
    strand.split(//).each_with_index do |char, index|
      if TORNA.keys.include?(char)
        strand[index] = TORNA[char]
      end
    end
    strand
  end
end
