class Complement
  MAPPING = {
    'C' => 'G',
    'G' => 'C',
    'T' => 'A',
    'A' => 'U',
  }

  class << self
    def of_dna strand
      of strand, :fetch
    end

    def of_rna strand
      of strand, :rassoc
    end

    private

    def of strand, method
      strand.chars.map { |x| MAPPING.public_send(method, x)[0] }.join
    end
  end

end
