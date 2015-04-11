class Complement
  # the cheesiest of case statements
  def self.of_dna(strand)
    result = []
    strand.chars.each do |i|
      case i
      when "G"
        result << "C"
      when "C"
        result << "G"
      when "T"
        result << "A"
      when "A"
        result << "U"
      end
    end
    result.join("")
  end

  def self.of_rna(strand)
    result = []
    strand.chars.each do |i|
      case i
      when "G"
        result << "C"
      when "C"
        result << "G"
      when "A"
        result << "T"
      when "U"
        result << "A"
      end
    end
    result.join("")
  end
end
