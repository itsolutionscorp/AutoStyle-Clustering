class Complement

  MAP = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }

  def self.of_dna(strand)
    transcribe(strand, MAP)
  end

  def self.of_rna(strand)
    transcribe(strand, MAP.invert)
  end

  private

  def self.transcribe(string, source)
    string.each_char.map { |nucleotide| source[nucleotide] }.join
  end

end
