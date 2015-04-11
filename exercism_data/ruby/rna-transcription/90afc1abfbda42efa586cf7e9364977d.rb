class Complement
  @complementary = {
      'G' => 'C', 
      'C' => 'G', 
      'T' => 'A',
      'A' => 'U'
    }
  def self.of_dna(dna)
    of_sequence(dna, @complementary)
  end

  def self.of_rna(rna)
    of_sequence(rna, @complementary.invert)
  end

  private 
  def self.of_sequence(sequence, mapping)
    raise ArgumentError unless sequence.each_char.all? {|char| mapping.keys.include?(char)}
    sequence.each_char.collect_concat {|char| mapping[char] }.join
  end
end
