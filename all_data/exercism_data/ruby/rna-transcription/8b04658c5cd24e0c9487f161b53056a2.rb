module Complement
  class Transform
    DNA_TRANSFORM = {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }

    RNA_TRANSFORM = DNA_TRANSFORM.invert

    def initialize(strand, transform)
      @strand = strand
      @transform = transform
    end

    def perform
      validate_attrs
      validate_strand
      @strand.chars.map{|c| @transform[c]}.join
    end

    private
    def validate_strand
      raise ArgumentError, 'Invalid character in strand' if (@strand.chars.uniq - @transform.keys).length > 0
    end

    def validate_attrs
      raise ArgumentError, 'Missing strand' unless @strand
      raise ArgumentError, 'Missing transform' unless @transform
    end

  end

  def self.of_rna(rna)
    Transform.new(rna, Transform::RNA_TRANSFORM).perform
  end
  def self.of_dna(dna)
    Transform.new(dna, Transform::DNA_TRANSFORM).perform
  end

end
