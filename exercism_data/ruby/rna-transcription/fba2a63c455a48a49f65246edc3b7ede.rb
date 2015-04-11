class Complement
  DNATORNA = {'G'=> 'C', 'C'=> 'G', 'T'=> 'A', 'A'=> 'U'}
  RNATODNA = {'C'=> 'G', 'G'=> 'C', 'A'=> 'T', 'U'=> 'A'}
  def self.of_dna(dnastrand)
    mapstrand(dnastrand, DNATORNA)
  end

  def self.of_rna(rnastrand)
    mapstrand(rnastrand, RNATODNA)
  end

  private 

  def self.mapstrand(strand, maphash)
    strand.chars.map { |nucleotide| maphash[nucleotide] }.join
  end
end
