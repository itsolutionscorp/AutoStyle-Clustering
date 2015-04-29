class Complement
  def self.of_dna(strand)
    complement = ""
    strand.length.times do |i|
      if strand[i] == 'C'
        complement << 'G'
      elsif strand[i] == 'G'
        complement << 'C'
      elsif strand[i] == 'T'
        complement << 'A'
      elsif strand[i] == 'A'
        complement << 'U'
      end
    end
    complement
  end

  def self.of_rna(strand)
    complement = ""
    strand.length.times do |i|
      if strand[i] == 'C'
        complement << 'G'
      elsif strand[i] == 'G'
        complement << 'C'
      elsif strand[i] == 'U'
        complement << 'A'
      elsif strand[i] == 'A'
        complement << 'T'
      end
    end
    complement
  end
end
