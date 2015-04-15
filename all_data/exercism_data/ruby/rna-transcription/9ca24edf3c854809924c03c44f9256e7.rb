def assert_is_string(maybe_string)
  raise "String expected" unless maybe_string.is_a? String
end

module Complement
  def self.dna_complementary_pairs
    { "A" => "U",
      "T" => "A",
      "C" => "G",
      "G" => "C" }
  end

  def self.rna_complementary_pairs
    Hash[self.dna_complementary_pairs.to_a.map { |a,b| [b,a] }] #TODO: memoize
  end

  def self.replace_nucleotides_with(strand, pairing)
    strand.gsub(/./, pairing)
  end

  def self.of_rna(rna)
    assert_is_string rna
    self.replace_nucleotides_with rna, rna_complementary_pairs
  end

  def self.of_dna(dna)
    assert_is_string dna
    self.replace_nucleotides_with dna, dna_complementary_pairs
  end

end
