class Complement
  @complements = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}

  def self.of_dna(strand)
    complement = ''
    strand.split("").each do |i|
      complement << @complements[i]
    end
    return complement
  end

  def self.of_rna(strand)
    complement = ''
    strand.split("").each do |i|
      complement << @complements.key(i)
    end
    return complement
  end
end
