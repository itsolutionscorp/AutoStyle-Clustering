class Nucleotide

  def self.code
    demodulized_name.last[0]
  end

  def self.demodulized_name
    name.split('::')
  end

  def to_rna
    self
  end

  def to_s
    self.class.code
  end

end

module Nucleotides

  def self.for_code(code)
    klass = constants.detect do |nucleotide|
      const_get(nucleotide).code == code
    end
    const_get klass
  end

  class Cytidine < ::Nucleotide; end

  class Guanosine < ::Nucleotide; end

  class Adenosine < ::Nucleotide; end

  class Uracil < ::Nucleotide; end

  class Thymidine < ::Nucleotide
    def to_rna
      Uracil.new
    end
  end

end

class Strand

  def initialize(*nucleotide_codes)
    @nucleotides = nucleotide_codes.map do |code|
      Nucleotides.for_code(code).new
    end
  end

  def to_rna
    @nucleotides.map! &:to_rna
    self
  end

  def to_s
    @nucleotides.map(&:to_s).join ''
  end

end

class DNA

  def initialize(strand_string)
    @strand = Strand.new strand_string.split ''
  end

  def to_rna
    @strand.to_rna.to_s
  end

end
