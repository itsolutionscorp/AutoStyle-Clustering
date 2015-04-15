class DNA
  attr_reader :nucleobases
  
  def initialize(nucleobases)
    @nucleobases = nucleobases
  end
  
  def to_rna
    nucleobases.tr(thymine, uracil)
  end
  
  private
  def uracil
    'U'
  end
  
  def thymine
    'T'
  end
end
