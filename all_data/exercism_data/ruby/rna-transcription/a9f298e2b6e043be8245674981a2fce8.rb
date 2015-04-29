class Complement
  @dna = ["G", "C", "T", "A"]
  @rna = ["C", "G", "A", "U"]

  def self.of_dna(strand)
    strand.chars.reduce("") do |sum, x|
      sum << @rna[@dna.rindex(x)]
    end
  end

  def self.of_rna(strand)
    strand.chars.reduce("") do |sum, x|
      sum << @dna[@rna.rindex(x)]
    end
  end
end
