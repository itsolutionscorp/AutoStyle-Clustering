class Complement
  DNA_TO_RNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(strand)
    strand.each_char.map { |nt|
      DNA_TO_RNA[nt]
    }.join
  end

  def self.of_rna(strand)
    strand.each_char.map { |nt|
      DNA_TO_RNA.key(nt)
    }.join
  end
end
