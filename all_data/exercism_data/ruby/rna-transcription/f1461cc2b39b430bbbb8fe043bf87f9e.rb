class Complement
attr_reader :bases

  def self.setup(strand)
    @bases = strand.chars
  end

  def self.of_dna(strand)
    setup(strand)
    compliment = @bases.map do |s|
      case s
      when "A" then "U"
      when "U" then "A"
      when "G" then "C"
      when "T" then "A"
      when "C" then "G"
      end
    end
    compliment.join
  end

  def self.of_rna(strand)
    setup(strand)
    compliment = @bases.map do |s|
      case s
      when "A" then "T"
      when "U" then "A"
      when "G" then "C"
      when "C" then "G"
      end
    end
    compliment.join
  end
end
