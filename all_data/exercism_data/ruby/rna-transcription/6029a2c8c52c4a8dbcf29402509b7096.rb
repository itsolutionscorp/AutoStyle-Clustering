class Complement

  def self.incoming_strand(strand)
    strand.split(//)
  end


  def self.of_dna(letters)
    incoming_strand(letters).map do |letter|
      case letter
      when "C" then "G"
      when "G" then "C"
      when "T" then "A"
      when "A" then "U"
      end
    end.join
  end

  def self.of_rna(letters)
    incoming_strand(letters).map do |letter|
      case letter
      when "G" then "C"
      when "C" then "G"
      when "A" then "T"
      when "U" then "A"
      end
    end.join
  end
end
