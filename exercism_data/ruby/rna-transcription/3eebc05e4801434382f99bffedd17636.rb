class Complement
    
  DNA_COMPS = {
    "C" => "G",
    "G" => "C",
    "A" => "U",
    "T" => "A"
  }

  RNA_COMPS = DNA_COMPS.invert

  def self.of_dna(input)
    self.iterate(DNA_COMPS, input)
  end

  def self.of_rna(input)
    self.iterate(RNA_COMPS, input)
  end

  private

  def self.iterate(comps, input)
    output = input.each_char.map { |char| comps[char] }
    output.join
  end
end
