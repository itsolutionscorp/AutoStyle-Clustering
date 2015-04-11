class Nucleotide

  def initialize(dna_string)
    raise ArgumentError if dna_string.match(/[^CGAT]+/)
    @dna_string = dna_string
  end

  def self.from_dna(dna_string)
    Nucleotide.new(dna_string)
  end

  def count(nuc)
    nuc_count = 0
    @dna_string.chars.each do |char|
      nuc_count += 1 if char == nuc
    end
    nuc_count
  end

  def histogram
    nuc_hash = {'A' => 0, 'C' => 0, 'G' => 0, 'T' => 0 }
    @dna_string.chars.each do |dna|
      nuc_hash[dna] +=1
    end
    nuc_hash
  end

end
