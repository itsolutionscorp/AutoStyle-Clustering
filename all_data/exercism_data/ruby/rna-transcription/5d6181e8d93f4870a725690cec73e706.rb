class Complement

  def self.of_dna(nucleotid)

    map = {'G' => 'C',
           'C' => 'G',
           'T' => 'A',
           'A' => 'U'}

    complement = []
    sequence = nucleotid.chars
    sequence.each do |i|
      complement.push(map[i])
    end
    return complement.join
  end

  def self.of_rna(nucleotid)

    map = {'C' => 'G',
           'G' => 'C',
           'A' => 'T',
           'U' => 'A'}
    complement = []

    sequence = nucleotid.chars
    sequence.each do |i|
      complement.push(map[i])
    end
    return complement.join
  end

end
