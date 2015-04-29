class Hamming

  class << self

    def compute(strand1, strand2)
      mutations(strand1, strand2).count
    end

    def mutations(strand1, strand2)
      strand1.chars.zip(strand2.chars).select do |nucleo1, nucleo2|
        mutation?(nucleo1, nucleo2)
      end
    end

    def mutation?(original_nucleo, potential_mutation)
      original_nucleo != potential_mutation unless potential_mutation.nil?
    end

  end

end
