class Complement
  DNA_TO_RNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(strand)
    transform strand do |nucleotide|
      DNA_TO_RNA.fetch(nucleotide)
    end
  end

  def self.of_rna(strand)
    transform strand do |nucleotide|
      DNA_TO_RNA.invert.fetch(nucleotide)
    end
  end

  private

  def self.transform(strand)
    strand.chars.map { |nucleotide| yield nucleotide }.join
  end
end
