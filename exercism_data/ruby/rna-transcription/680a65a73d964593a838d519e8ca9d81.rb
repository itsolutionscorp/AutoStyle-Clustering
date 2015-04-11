class Complement

  RNA_TO_DNA  = { "C" => "G",
                  "G" => "C",
                  "A" => "T",
                  "U" => "A"
                }

  DNA_TO_RNA  = { "G" => "C",
                  "C" => "G",
                  "T" => "A",
                  "A" => "U"
                }

  def self.of_dna(strand)
    nucleotides(strand).each_with_object("") do |nucleotide, complement|
      complement << convert_to_rna(nucleotide)
    end
  end

  def self.of_rna(strand)
    nucleotides(strand).each_with_object("") do |nucleotide, complement|
      complement << convert_to_dna(nucleotide)
    end
  end

  private

  def self.convert_to_rna(nucleotide)
    DNA_TO_RNA[nucleotide]
  end

  def self.convert_to_dna(nucleotide)
    RNA_TO_DNA[nucleotide]
  end

  def self.nucleotides(strand)
    strand.chars
  end

end
