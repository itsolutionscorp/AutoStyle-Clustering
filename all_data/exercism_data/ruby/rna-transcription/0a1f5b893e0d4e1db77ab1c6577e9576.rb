class Complement
  MAPPING = {
    :dna => { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' },
    :rna => { 'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A' }
    }

  def self.of_dna(dna)
    self.translate_codes dna, :dna
  end

  def self.of_rna(rna)
    self.translate_codes rna, :rna
  end

  def self.translate_codes(input, type)
    input.each_char.map do |char|
      MAPPING[type][char]
    end.join
  end
end
