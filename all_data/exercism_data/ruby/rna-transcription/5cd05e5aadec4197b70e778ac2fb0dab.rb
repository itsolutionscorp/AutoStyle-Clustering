class Complement
  DNA_MAP = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  RNA_MAP = DNA_MAP.invert

  def self.of_dna(nucleotide)
    if nucleotide.length > 1
      nucleotide.chars.map{|n| DNA_MAP[n] }.join
    else
      DNA_MAP[nucleotide]
    end
  end

  def self.of_rna(nucleotide)
    if nucleotide.length > 1
      nucleotide.chars.map{|n| RNA_MAP[n] }.join
    else
      RNA_MAP[nucleotide]
    end
  end

end
