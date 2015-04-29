class Complement
  DNA_TO_RNA = { 
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }

  class << self
    def of_dna(strand)
      compliment(strand) { |char| DNA_TO_RNA.fetch char }
    end

    def of_rna(strand)
      compliment(strand) { |char| DNA_TO_RNA.key char }
    end

    private

    def compliment(strand, &block)
      strand.split(//).map { |char| block.call char }.join
    end
  end
end
