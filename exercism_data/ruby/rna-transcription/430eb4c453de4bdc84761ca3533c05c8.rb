class Complement
  def self.of_dna(dna_strand)
    dictionary = {"C" => "G", "G" => "C", "T" => "A", "A"=> "U"}
    convert_strands(dna_strand, dictionary)
  end

  def self.of_rna(rna_strand)
    dictionary = {"C" => "G", "G" => "C", "A" => "T", "U"=> "A"}
    convert_strands(rna_strand, dictionary)
  end

  def self.convert_strands(strand, dictionary)
    result_string = strand.chars.reduce("") { |result, letter| result + dictionary[letter] }
    result_string
  end
end
