class Complement
  DICT = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }

  class << self
    def of_dna(input)
      convert(DICT, input)
    end

    def of_rna(input)
      convert(DICT.invert, input)
    end

  private
    def convert(dict, input)
      input.split("").map { |c| dict[c] || raise(ArgumentError) }.join
    end
  end
end
