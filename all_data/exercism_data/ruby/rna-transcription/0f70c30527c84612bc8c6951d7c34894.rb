class Complement
  def self.of_dna(strand)
    hash = { 'C' => 'G', 'G' => 'C', 'T' => 'A', 'A' => 'U'}
    strand.chars.map {|c| c = hash[c]}.join
  end
  
  def self.of_rna(strand)
    hash = {'C' => 'G', 'G' => 'C', 'U' => 'A', 'A' => 'T'}
    strand.chars.map {|c| c = hash[c]}.join
  end
end
