class Complement
  TRANSCRIPTIONS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  
  def self.of_dna(strand)
    transcribe(strand, TRANSCRIPTIONS)
  end
  
  def self.of_rna(strand)
    transcribe(strand, TRANSCRIPTIONS.invert)
  end
  
  private
  
  def self.transcribe(strand, rules)
    strand.chars.each_with_object("") do |nucleotide, transcription|
      transcription << rules.fetch(nucleotide) { raise ArgumentError }
    end
  end
end

# Parting words: I'm raising the error in the block instead of setting it
# as the second argument in `fetch` because otherwise it'd get appended
# to the output string
