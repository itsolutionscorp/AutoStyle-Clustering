class Complement
  @complements = {'C' => 'G',
                 'G' => 'C',
                 'T' => 'A',
                 'A' => 'U'}

  def self.of_dna(dna_strand)
    dna_strand.chars.map{|x| @complements[x]}.join
  end

  def self.of_rna(rna_strand)
    rna_strand.chars.map{|x| @complements.rassoc(x)[0]}.join
  end
end
