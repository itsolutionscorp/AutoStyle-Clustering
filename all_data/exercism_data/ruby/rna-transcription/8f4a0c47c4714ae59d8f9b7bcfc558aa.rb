class Complement
  
  def self.of_dna(sequence)
    result_sequence = []
    sequence.split("").each do |nucleotide|      
      case nucleotide
      when "G"
        result_sequence << "C"
      when "C"
        result_sequence << "G"
      when "A"
        result_sequence << "U"
      when "T"
        result_sequence << "A"
      else
        Logger.new("Mutation!")
      end
    end
    result_sequence.join("")
  end  
  def self.of_rna(sequence)
    result_sequence = []
    sequence.split("").each do |nucleotide|      
      case nucleotide
      when "C"
        result_sequence << "G"
      when "G"
        result_sequence << "C"
      when "A"
        result_sequence << "T"
      when "U"
        result_sequence << "A"
      else
        Logger.new("Mutation!")
      end
    end
    result_sequence.join("")
  end  
  
end
