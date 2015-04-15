class DNA

  MAPPING = {
    'C' => 'C',
    'G' => 'G',
    'A' => 'A',
    'T' => 'U'
  }

  def initialize(dna)
    @dna = dna
  end

  # While a simple `@string.gsub('T', 'U')` would be enough
  # to make the tests pass, I wanted take the DNA alphabet
  # into consideration. This way it's not possible to transcribe
  # invalid DNA.
  def to_rna
    @dna.gsub(/./) { |base| transcribe(base) }
  end

private

  def transcribe(base)
    MAPPING.fetch(base) { raise "Invalid DNA. Cannot transcribe #{base}." }
  end

end
