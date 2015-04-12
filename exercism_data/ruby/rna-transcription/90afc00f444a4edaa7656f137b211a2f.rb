class Complement
    
  DNA_COMPS = {
    C: "G",
    G: "C",
    A: "U",
    T: "A"
  }

  RNA_COMPS = {
    C: "G",
    G: "C",
    A: "T",
    U: "A"
  }

  def self.of_dna(input)
    self.iterate(DNA_COMPS, input)
  end

  def self.of_rna(input)
    self.iterate(RNA_COMPS, input)
  end

  private

  def self.iterate(comps, input)
    output = ''
    input.each_char { |char| output << comps[char.to_sym] }
    output
  end
end