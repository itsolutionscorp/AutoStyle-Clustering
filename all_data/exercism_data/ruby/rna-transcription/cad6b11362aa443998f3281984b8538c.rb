class Complement
  class << self
    def of_dna(dna)
      String Strand.create(dna).to_rna
    end

    def of_rna(rna)
      String Strand.create(rna).to_dna
    end
  end
end

class Strand
  class << self
    def create(nucleotides)
      new nucleotides.chars.map { |c| Nucleotide.new c }
    end
  end

  attr_reader :nucleotides

  def initialize(nucleotides)
    @nucleotides = nucleotides
  end

  def to_str
    nucleotides.map(&:value).join
  end

  def to_rna
    complement :rna
  end

  def to_dna
    complement :dna
  end

  private

  def complement(type)
    self.class.new nucleotides.map { |n| n.complement type }
  end

  class Nucleotide
    COMPLEMENT_MAP = {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }

    attr_reader :value

    def initialize(value)
      @value = value
    end

    def complement(type)
      new_value = case type
      when :dna; COMPLEMENT_MAP.invert[value]
      when :rna; COMPLEMENT_MAP[value]
      else
        raise ArgumentError, "#{type} is unknown"
      end
      self.class.new new_value
    end
  end
end
