class Complement

  DNA_COMPLEMENTS = {"A" => "U", "C" => "G", "G" => "C", "T" => "A"}
  RNA_COMPLEMENTS = {"U" => "A", "G" => "C", "C" => "G", "A" => "T"}

  def self.of_dna(dna)
    dna.split('').map do |nucleotide|
      DNA_COMPLEMENTS[nucleotide]
    end.join('')
  end

  def self.of_rna(rna)
    rna.split('').map do |nucleotide|
      RNA_COMPLEMENTS[nucleotide]
    end.join('')
  end

end
