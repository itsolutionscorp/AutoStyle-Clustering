class Complement

  @@rna_complements = {
   "G" => "C",
   "C" => "G",
   "A" => "U",
   "T" => "A"
  }

  @@dna_complements = {
   "G" => "C",
   "C" => "G",
   "A" => "T",
   "U" => "A"
  }

  def self.of_rna(strand)
    result = []
    strand.each_char { |c| result << @@dna_complements[c] }
    result.join()
  end

  def self.of_dna(strand)
    result = []
    strand.each_char { |c| result << @@rna_complements[c] }
    result.join()
  end
end
