class Complement
  RNA = { 'C'=>'G', 'G'=>'C', 'T'=>'A', 'A'=>'U' }
  DNA = { 'C'=>'G', 'G'=>'C', 'U'=>'A', 'A'=>'T' }
  
  def self.of_dna(strand)
    strand.each_char.map { |nuc|
      RNA.fetch(nuc)
    }.join
  end

  def self.of_rna(strand)
    strand.each_char.map { |nuc|
      DNA.fetch(nuc)
    }.join
  end
end
