class Complement
  DNA_RNA = { 'G' => 'C',
              'C' => 'G',
              'T' => 'A',
              'A' => 'U' }

  RNA_DNA = { 'G' => 'C',
              'C' => 'G',
              'U' => 'A',
              'A' => 'T' }

  def self.of_dna(strand)
    strand.gsub(/./) { |base| DNA_RNA[base] }
  end

  def self.of_rna(strand)
    strand.gsub(/./) { |base| RNA_DNA[base] }
  end
end
