class Complement
  @dna_map = {"A" => "U", "C" => "G", "G" => "C", "T" => "A"}

  def self.of_dna(strand)
    rna = ""
    strand.each_char do |n|
      raise ArgumentError.new("Wrong nucleotides") unless @dna_map[n]
      rna += @dna_map[n]
    end
    return rna
  end

  def self.of_rna(strand)
   dna = ""
    strand.each_char do |n|
      raise ArgumentError.new("Wrong nucleotides") unless @dna_map.key(n)
      dna += @dna_map.key(n)
    end
    return dna
  end
end
