class Complement
  attr_reader :corresponding_element

  def self.of_dna(rna)
    split_rna = rna.chars.map do |rna|
    corresponding_element[rna]
    end
    split_rna.join
  end

  def self.corresponding_element
    corresponding_element = {"C" => "G", "G" => "C","T" => "A","A" => "U"}
  end

  def self.reversed_corresponding_element
    corresponding_element.invert
  end

  def self.of_rna(dna)
    split_dna = dna.chars.map do |dna|
    reversed_corresponding_element[dna]
    end
    split_dna.join
  end
end
