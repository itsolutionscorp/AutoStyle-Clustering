class Complement
  DNA_HASH = {G: "C", C: "G", T: "A", A: "U"}

  def self.of_dna(nucleo)
    nucleo.chars.map {|letter| DNA_HASH[letter.to_sym]}.join
  end

  def self.of_rna(nucleo)
    nucleo.chars.map {|letter| DNA_HASH.key(letter).to_s}.join
  end
end
