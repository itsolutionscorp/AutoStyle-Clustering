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

  def self.compliment(strand, &block)
    strand.each_char.inject('') { |a, e| a << block.call(e) }
  end

  private_class_method :compliment
end
