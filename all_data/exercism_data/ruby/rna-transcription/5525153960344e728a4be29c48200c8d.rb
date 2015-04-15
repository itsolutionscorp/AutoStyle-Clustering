class Complement
  
  def self.of_dna(strand)
    strand.gsub(/[CGTAU]/, {"C" => "G", "G" => "C", "T" => "A", "A" => "U"})
  end
  
  def self.of_rna(strand)
    strand.gsub(/[CGTAU]/, {"C" => "G", "G" => "C", "U" => "A", "A" => "T"})
  end
end
