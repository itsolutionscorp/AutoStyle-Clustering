class Complement

  def self.of_dna(input)
    complement_generator(input, 'dna')
  end

  def self.of_rna(input)
    complement_generator(input, 'rna')
  end

  private

  def self.complement_generator(input, type)

    complement = {
        'G' => 'C',
        'C' => 'G',
        'T' => 'A',
        'A' => 'U'
    }

    input.chars.map do |nucleotide|
      if type == 'dna'
        complement[nucleotide]
      else
        'rna'
        complement.key(nucleotide)
      end
    end.join
  end

end
