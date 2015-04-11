class Complement

  def self.of_dna(dna) 
    convert_to_complement dna, complement_map
  end

  def self.of_rna(rna)
    convert_to_complement rna, complement_map.invert
  end

  private

  def self.complement_map
    @@complement_map ||= {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  end

  def self.convert_to_complement(strand, map)
    (0..strand.length - 1).inject('') do |complementary_strand, index|
      complementary_strand += map[strand[index]]
    end
  end
end
