class Complement
  DNA = %w(T C G A)
  RNA = %w(A G C U)

  def self.of_dna(rna)
    comp = Hash[*DNA.zip(RNA).flatten]
    rna.chars.inject('') {|dna, r| dna + comp[r] }
  end

  def self.of_rna(dna)
    comp = Hash[*RNA.zip(DNA).flatten]
    dna.chars.inject('') {|rna, d| rna + comp[d] }
  end
end
