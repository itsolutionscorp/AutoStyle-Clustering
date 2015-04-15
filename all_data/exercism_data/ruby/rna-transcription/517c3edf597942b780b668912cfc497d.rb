class Complement
  DNA_TO_RNA = {
    'C' => 'G',
    'G' => 'C',
    'T' => 'A',
    'A' => 'U',
  }

  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(strand)
    strand.each_char.map do |stran|
      DNA_TO_RNA[stran]
    end.join
  end

  def self.of_rna(strand)
    strand.each_char.map do |stran|
      RNA_TO_DNA[stran]
    end.join
  end
end
