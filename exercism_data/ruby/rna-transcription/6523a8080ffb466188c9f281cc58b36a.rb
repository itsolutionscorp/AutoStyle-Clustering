# complement.rb

class Complement

  PAIRS = [['G', 'C'],['C','G'],['T','A'],['A','U']]

  # array.to_h exists Ruby 2.1
  DNA_TO_RNA = PAIRS.inject({}){|hash, a| hash[a[0]] = a[1]; hash}
  RNA_TO_DNA = PAIRS.inject({}){|hash, a| hash[a[1]] = a[0]; hash}

  def self.of_dna dna_string
    dna_string.chars.map { |dna| DNA_TO_RNA[dna] }.join
  end

  def self.of_rna rna_string
    rna_string.chars.map { |rna| RNA_TO_DNA[rna] }.join
  end

end
