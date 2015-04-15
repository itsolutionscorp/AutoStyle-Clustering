class Complement
  DNA_TO_RNA = {
    C: 'G',
    G: 'C',
    T: 'A',
    A: 'U'
  }
  RNA_TO_DNA = {
    G: 'C',
    C: 'G',
    A: 'T',
    U: 'A'
  }

  def self.of_dna strand
    strand.split('').map{ |nucleotide| DNA_TO_RNA[nucleotide.to_sym] }.join
  end

  def self.of_rna strand
    strand.split('').map{ |nucleotide| RNA_TO_DNA[nucleotide.to_sym] }.join
  end
end
