class Complement
  def self.of_dna(strand)
    table =  { G: 'C', C: 'G', T: 'A', A: 'U' }
    strand.split("").map { |k| table[k.to_sym]}.join("").to_s
  end

  def self.of_rna(strand)
    table =  { C: 'G', G: 'C', A: 'T', U: 'A' }
    strand.split("").map { |k| table[k.to_sym]}.join("").to_s
  end

end
