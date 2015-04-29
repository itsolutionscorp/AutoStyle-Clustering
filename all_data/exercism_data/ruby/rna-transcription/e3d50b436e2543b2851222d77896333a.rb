class Complement

  DNA = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  RNA = {'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A'}

  def self.of_dna dna
    convert dna, DNA
  end

  def self.of_rna rna
    convert rna, RNA
  end

  def self.convert(strand, lookup)
    (strand.chars.map {|c| lookup[c]}).join
  end

end
