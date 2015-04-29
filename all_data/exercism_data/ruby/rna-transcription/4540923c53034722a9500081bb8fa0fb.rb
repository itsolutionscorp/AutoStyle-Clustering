class Complement

  @swap ={
    G: "C",
    C: "G",
    T: "A",
    A: "U"
  }
  
  def self.of_dna(dna_nucleotide)
    dna_nucleotide.split("").each_with_object(""){|letter, rna| rna << @swap[letter.to_sym]}  
  end

  def self.of_rna (rna_nucleotide)
    rna_nucleotide.split("").each_with_object(""){|letter, dna| dna << @swap.invert[letter].to_s}
  end
end
