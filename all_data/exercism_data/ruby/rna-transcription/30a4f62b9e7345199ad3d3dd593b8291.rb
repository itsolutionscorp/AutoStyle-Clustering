class DNA

  THYMINE = 'T'
  URACIL = 'U'

  def initialize(sequence)
    @sequence = String(sequence)
  end

  def to_rna
    replace_thymine_with_uracil
  end

  private
    attr_reader :sequence

    def replace_thymine_with_uracil
      sequence.gsub(/#{THYMINE}/, URACIL)
    end
end
