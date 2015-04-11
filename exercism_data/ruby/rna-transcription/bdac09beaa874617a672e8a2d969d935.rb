class Complement
  COMPLEMENTARY = { "A" => "U", "C" => "G", "G" => "C", "T" => "A" }
  INVERTED      = COMPLEMENTARY.invert

  def self.of_dna seq
    seq.gsub(/./) { |m| COMPLEMENTARY.fetch(m, m) }
  end

  def self.of_rna seq
    seq.gsub(/./) { |m| INVERTED.fetch(m, m) }
  end
end
