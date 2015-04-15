class DNA

  attr_reader :source

  def initialize(source)
    @source = source
  end

  def to_rna
    source.gsub('T', 'U')
  end

end
