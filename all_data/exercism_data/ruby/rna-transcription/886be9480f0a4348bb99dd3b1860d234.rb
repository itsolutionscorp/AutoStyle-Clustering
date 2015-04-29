class Complement
  DNA_HASH = {G: "C", C: "G", T: "A", A: "U"}

  def self.of_dna(nucleo)
    rna_string = "" 
    nucleo.chars.each do |letter|
      rna_string += DNA_HASH[letter.to_sym]
    end
    rna_string 
  end

  def self.of_rna(nucleo)
    dna_string = "" 
    nucleo.chars.each do |letter|
      dna_string += DNA_HASH.key(letter).to_s
    end
    dna_string 
  end
end
