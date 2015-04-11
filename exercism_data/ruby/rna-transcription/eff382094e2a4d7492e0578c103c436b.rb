class DNA

  attr_reader :source

  THYMIDINE = 'T'
  URACIL = 'U'

  def initialize(source)
    @source = source
  end

  def to_rna
    source.tr(THYMIDINE, URACIL)
  end

end
