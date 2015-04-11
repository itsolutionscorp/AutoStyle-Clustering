class Complement

  def self.of_dna(nucleotide)

    map = {'G' => 'C',
           'C' => 'G',
           'T' => 'A',
           'A' => 'U'}

    complement = []
    sequence = nucleotide.chars
    sequence.each do |i|
      complement.push(map[i])
    end
    return complement.join
  end

  def self.of_rna(nucleotide)

    map = {'C' => 'G',
           'G' => 'C',
           'A' => 'T',
           'U' => 'A'}
    complement = []

    sequence = nucleotide.chars
    sequence.each do |i|
      complement.push(map[i])
    end
    return complement.join
  end

end
