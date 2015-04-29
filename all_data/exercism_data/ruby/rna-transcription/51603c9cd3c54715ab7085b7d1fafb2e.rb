class Complement
  def self.of_dna(input)
    dna_comps = {
      A: "U",
      T: "A"
    }
    self.iterate(dna_comps, input)
  end

  def self.of_rna(input)
    rna_comps = {
      A: "T",
      U: "A"
    }
    self.iterate(rna_comps, input)
  end

  private

  def self.iterate(unique_comps, input)
    common_comps = {
      C: "G",
      G: "C"
    }
    xna_comps = common_comps.merge(unique_comps)
    input_arr = input.split('')
    output = []
    input_arr.each { |a| output << xna_comps[a.to_sym] }
    output.join('')
  end
end
