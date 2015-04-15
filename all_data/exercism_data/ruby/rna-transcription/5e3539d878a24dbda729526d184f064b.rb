# class Complement
#   def self.of_dna(dna)
#     dna_strands = ["G", "C", "T", "A"]
#     rna_strands = ["C", "G", "A", "U"]

#     converted_string = ""
#     dna.split("").each do |dna_letter|
#       converted_string[converted_string.length] = rna_strands[dna_strands.index(dna_letter)]
#     end
#     return converted_string
#   end

#   def self.of_rna(rna)
#     dna_strands = ["G", "C", "T", "A"]
#     rna_strands = ["C", "G", "A", "U"]

#     converted_string = ""
#     rna.split("").each do |rna_letter|
#       converted_string[converted_string.length] = dna_strands[rna_strands.index(rna_letter)]
#     end
#     return converted_string
#   end
# end

class Complement
  DNA_AND_RNA_CONVERTERS = {
    DNA: { C: :G, G: :C, T: :A, A: :U },
    RNA: { C: :G, G: :C, U: :A, A: :T },
  }

  [:DNA, :RNA].each do |molecule|
    self.define_singleton_method("of_#{molecule.downcase}") do |nbases|
      nbases.split('').map { |nbase| DNA_AND_RNA_CONVERTERS[molecule][nbase.to_sym].to_s }.join('')
    end
  end
end
