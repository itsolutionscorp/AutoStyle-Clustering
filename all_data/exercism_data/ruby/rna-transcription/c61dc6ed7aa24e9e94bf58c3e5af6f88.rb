class Complement

  @complement_pairs = { "G" => "C", "C" => "G", "T" => "A", "A" => "U" }

  def self.of_dna(dna)
    rna = ""
    dna.each_char do |c|
      raise ArgumentError, 'Invalid DNA Character' if @complement_pairs[c].nil?
      rna << @complement_pairs[c].to_s
    end
    rna
  end

  def self.of_rna(rna)
    dna = ""
    rna.each_char do |c|
      raise ArgumentError, 'Invalid RNA Character' if @complement_pairs.key(c).nil?
      dna << @complement_pairs.key(c).to_s
    end
    dna
  end
end
