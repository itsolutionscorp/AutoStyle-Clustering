class Complement

  FROM_DNA = {'A' => 'U', 'T' => 'A', 'C' => 'G', 'G' => 'C'}
  FROM_RNA = {'U' => 'A', 'A' => 'T', 'C' => 'G', 'G' => 'C'}

  def self.of_dna(dna)
    dna.chars.map do |char|
      FROM_DNA[char]
    end .join('')
  end

  def self.of_rna(rna)
    rna.chars.map do |char|
      FROM_RNA[char]
    end .join('')
  end
end
