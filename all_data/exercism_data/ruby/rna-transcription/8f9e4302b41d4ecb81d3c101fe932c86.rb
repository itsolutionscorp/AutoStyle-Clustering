class Complement
  
  NUCLEOTIDES = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }
  
  def self.of_dna(sequence)
    result_sequence = []
    sequence.each_char.map do |char|
      result_sequence << NUCLEOTIDES[char]
    end
    result_sequence.join("")
  end  
  
  def self.of_rna(sequence)
    result_sequence = []
    sequence.each_char.map do |char|
      result_sequence << NUCLEOTIDES.key(char)
    end
    result_sequence.join("")
  end  
  
end
