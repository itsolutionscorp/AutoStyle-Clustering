class Complement

  class << self

    # * `G` -> `C`
    # * `C` -> `G`
    # * `T` -> `A`
    # * `A` -> `U`
    COMPLEMENTS = {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }

    def of_dna(dna)
      dna.split('').inject('') { |rna, x| rna << COMPLEMENTS[x] }
    end

    def of_rna(rna)
      rna.split('').inject('') { |dna, x| dna << COMPLEMENTS.key(x) }
    end
  end
end
