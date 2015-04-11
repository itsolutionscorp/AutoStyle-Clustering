class Complement
  TO_RNA = Hash['G', 'C', 'C', 'G', 'T', 'A', 'A', 'U']
  TO_DNA = TO_RNA.invert

  def self.of_dna(strand)
    transcribe strand, TO_RNA
  end

  def self.of_rna(strand)
    transcribe strand, TO_DNA
  end

  private

  def self.transcribe(strand, transcriber)
    strand.chars.map do |v|
      fail ArgumentError unless transcriber.keys.include? v
      transcriber[v]
    end.join
  end
end
