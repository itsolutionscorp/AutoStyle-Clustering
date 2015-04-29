class DNA
  attr_reader :sequence

  SYMBOLS = { thymidine: 'T', uridine: 'U' }.freeze

  def initialize(sequence)
    @sequence = sequence
  end

  def to_rna
    sequence.gsub(SYMBOLS[:thymidine], SYMBOLS[:uridine])
  end
end
