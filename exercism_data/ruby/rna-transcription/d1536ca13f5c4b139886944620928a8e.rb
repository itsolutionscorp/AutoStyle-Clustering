class DNA
  attr_reader :sequence

  def initialize(sequence)
    @sequence = sequence
  end

  def to_rna
    letters.collect { |ltr| ltr == 'T' ? 'U' : ltr }.join
  end

  private

  def letters(&block)
    sequence.each_char.each(&block)
  end
end
