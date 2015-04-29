class Complement

  COMPLEMENTS_OF_DNA = {
    :G => "C",
    :C => "G",
    :T => "A",
    :A => "U"
  }

  COMPLEMENTS_OF_RNA = {
    :C => "G",
    :G => "C",
    :A => "T",
    :U => "A"
  }

  def self.of_dna(nucleo)
    process_nucleotides(nucleo, "dna")
  end

  def self.of_rna(nucleo)
    process_nucleotides(nucleo, "rna")
  end

  private
  def self.process_nucleotides(nucleotides, complement)
    transformed = ""
    method = "transform_#{complement}"
    nucleotides.each_char do |n|
      transformed << send(method, n)
    end
    transformed
  end

  def self.transform_dna(nucleotide)
    COMPLEMENTS_OF_DNA[nucleotide.to_sym]
  end

  def self.transform_rna(nucleotide)
    COMPLEMENTS_OF_RNA[nucleotide.to_sym]
  end
end
