class Complement
  def self.of_dna(input)
    dna_comps = {
      C: "G",
      G: "C",
      T: "A",
      A: "U"
    }
    input_arr = input.split('')
    comp = []
    input_arr.each { |a| comp << dna_comps[a.to_sym] }
    comp.join('')
  end

  def self.of_rna(input)
    rna_comps = {
      C: "G",
      G: "C",
      U: "A",
      A: "T"
    }
    input_arr = input.split('')
    comp = []
    input_arr.each { |a| comp << rna_comps[a.to_sym] }
    comp.join('')
  end
end
