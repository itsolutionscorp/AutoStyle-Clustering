class Complement
  def self.of_dna(strand)
    new_strand = ""
    strand.each_char do |nucleotides|
      new_strand << "G" if nucleotides == "C"
      new_strand << "C" if nucleotides == "G"
      new_strand << "A" if nucleotides == "T"
      new_strand << "U" if nucleotides == "A"
    end
   new_strand
  end

  def self.of_rna(strand)
    new_strand = ""
    strand.each_char do |nucleotides|
      new_strand << "G" if nucleotides == "C"
      new_strand << "C" if nucleotides == "G"
      new_strand << "A" if nucleotides == "U"
      new_strand << "T" if nucleotides == "A"
    end
   new_strand
  end
end
