class Complement

  attr_reader :hash, :rna

  def self.of_dna(strand)
    hash = {"G" => "C",
            "C" => "G",
            "T" => "A",
            "A" => "U"}
    rna = []
    strand_array = strand.chars

    strand_array.each do |e|
      if hash[e] == nil
        raise ArgumentError
      else
        rna << hash[e]
      end
    end
    rna.join
  end

  def self.of_rna(strand)
    hash = {"C" => "G",
            "G" => "C",
            "A" => "T",
            "U" => "A"}
    dna = []
    strand_array = strand.chars

    strand_array.each do |e|
      if hash[e] == nil
        raise ArgumentError
      else
        dna << hash[e]
      end
    end
    dna.join
  end
end

puts Complement.of_dna('G')
puts Complement.of_rna('U')
