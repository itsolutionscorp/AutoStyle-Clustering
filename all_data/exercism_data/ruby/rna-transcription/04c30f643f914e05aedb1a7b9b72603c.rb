class Complement

  def self.of_dna(strand)
    dna_to_rna = []

    dna_complements = {
      :C => 'G',
      :G => 'C',
      :T => 'A',
      :A => 'U'
    }

    dna_strand = strand.split('')

    dna_strand.each do |letter|
      dna_to_rna << dna_complements[letter.to_sym]
    end

    dna_to_rna.join

  end

  def self.of_rna(strand)
    rna_to_dna = []

    rna_complements = {
      :C => 'G',
      :G => 'C',
      :U => 'A',
      :A => 'T'
    }

    rna_strand = strand.split('')

    rna_strand.each do |letter|
      rna_to_dna << rna_complements[letter.to_sym]
    end

    rna_to_dna.join

  end

end
