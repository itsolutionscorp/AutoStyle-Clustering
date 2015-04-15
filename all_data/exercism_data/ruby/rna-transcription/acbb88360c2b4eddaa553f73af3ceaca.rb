class Complement

  def self.of_dna dna
    map_sequence dna, SEQUENCE_KEY, 'U'
  end

  def self.of_rna rna
    map_sequence rna, SEQUENCE_KEY.invert, 'T'
  end

  private

  SEQUENCE_KEY = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }

  def self.map_sequence string, hash, error_char
    raise ArgumentError if string.include? error_char
    string.chars.each.map { |c| hash[c] }.join
  end
end
