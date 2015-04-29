class Complement
  def self.of_dna(dna)
    string = dna
    rna = []
    string.each_char do |nucleotide|
      case nucleotide
      when 'C' then rna << 'G'
      when 'G' then rna << 'C'
      when 'T' then rna << 'A'
      when 'A' then rna << 'U'
      end
    end
    rna.join
  end

  def self.of_rna(rna)
    string = rna
    dna = []
    string.each_char do |nucleotide|
      case nucleotide
      when 'C' then dna << 'G'
      when 'G' then dna << 'C'
      when 'A' then dna << 'T'
      when 'U' then dna << 'A'
      end
    end
    dna.join
  end
end
