class Complement
  def self.of_dna(dna_string)
    unless @dna_complements
      @dna_complements = {
        'G' => 'C',
        'C' => 'G',
        'T' => 'A',
        'A' => 'U'
      }
    end
    (dna_string.chars.map { |e| @dna_complements[e] }).join
  end

  def self.of_rna(rna_string)
    unless @rna_complements
      @rna_complements = {
        'G' => 'C',
        'C' => 'G',
        'U' => 'A',
        'A' => 'T'
      }
    end
    (rna_string.chars.map { |e| @rna_complements[e] }).join
  end
end
