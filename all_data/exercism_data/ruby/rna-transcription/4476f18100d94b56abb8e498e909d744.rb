class Complement
  DNATORNA = {'G'=> 'C', 'C'=> 'G', 'T'=> 'A', 'A'=> 'U'}
  RNATODNA = {'C'=> 'G', 'G'=> 'C', 'A'=> 'T', 'U'=> 'A'}
  def self.of_dna(dnastrand)
    rnastrand = dnastrand.chars.map do |nucleotide|
      DNATORNA[nucleotide]
    end
    rnastrand.join
  end

  def self.of_rna(rnastrand)
    dnastrand = rnastrand.chars.map do |nucleotide|
      RNATODNA[nucleotide]
    end
    dnastrand.join
  end
end
