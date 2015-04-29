class Complement

  def self.of_dna(s)
    strand = ""
    s.each_char {|x| strand << find_dna_complement_of(x) }
    strand
  end

  def self.of_rna(s)
    strand = ""
    s.each_char {|x| strand << find_rna_complement_of(x) }
    strand
  end

  def self.find_dna_complement_of(n)
    case n
    when "C"
      return "G"
    when "G"
      return "C"
    when "T"
      return "A"
    when "A"
      return "U"
    end
  end

  def self.find_rna_complement_of(n)
    case n
    when "G"
      return "C"
    when "C"
      return "G"
    when "A"
      return "T"
    when "U"
      return "A"
    end
  end
end
