require 'pry'
class Complement

  def self.of_dna(dna)
    nucletodies = []
    dna.each_char do |char|
      nucletodies << char
    end
    answer = ""
    nucletodies.each_with_index do |nuc, index|
      if nuc == "G"
        answer += 'C'
      elsif nuc == "C"
        answer += 'G'
      elsif nuc == "T"
        answer += 'A'
      elsif nuc == "A"
        answer += "U"
      else
        puts "Not a valid DNA nucleotide"
      end
    end
   answer
  end

  def self.of_rna(rna)
    nucletodies = []
    rna.each_char do |char|
      nucletodies << char
    end
    answer =""
    nucletodies.each_with_index do |nuc, index|
      if nuc == "C"
        answer += 'G'
      elsif nuc == "G"
        answer += 'C'
      elsif nuc == "A"
        answer += 'T'
      elsif nuc == "U"
        answer += "A"
      else
        puts "Not a valid RNA nucleotide"
      end
    end
  answer
  end

end

# * `G` -> `C`
# * `C` -> `G`
# * `T` -> `A`
# * `A` -> `U`
# The four nucleotides found in DNA are adenine (**A**), cytosine (**C**),
# guanine (**G**) and thymidine (**T**).

# The four nucleotides found in RNA are adenine (**A**), cytosine (**C**),
# guanine (**G**) and uracil (**U**).
