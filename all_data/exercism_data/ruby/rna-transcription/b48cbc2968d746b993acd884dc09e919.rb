class Complement
  DNA_RNA = { 'G' => 'C',
              'C' => 'G',
              'T' => 'A',
              'A' => 'U' }

  def self.of_dna(strand)
    strand.gsub(/./) { |base| DNA_RNA[base] }
  end

  def self.of_rna(strand)
    strand.gsub(/./) { |base| DNA_RNA.invert[base] }
  end
end
