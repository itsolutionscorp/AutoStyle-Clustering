class Complement
  DNA_TO_RNA = { 
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(strand)
    compliment(strand) { |char| DNA_TO_RNA.fetch char }
  end

  def self.of_rna(strand)
    compliment(strand) { |char| DNA_TO_RNA.key char }
  end

  class << self
    private

    def compliment(strand, &block)
      strand.split(//).map { |char| block.call char }.join
    end
  end
end
