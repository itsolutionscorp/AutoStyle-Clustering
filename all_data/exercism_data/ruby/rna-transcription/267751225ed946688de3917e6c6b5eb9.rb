class Complement
  class << self
    def of_dna strand
      trans strand, DNA_TO_RNA
    end

    def of_rna strand
      trans strand, RNA_TO_DNA
    end

    private
    DNA_TO_RNA = {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }

    RNA_TO_DNA = DNA_TO_RNA.invert

    def trans strand, assoc
      chars = strand.each_char.map do |type|
        assoc[type] || (throw "Unhandled type")
      end

      chars.join
    end
  end
end
