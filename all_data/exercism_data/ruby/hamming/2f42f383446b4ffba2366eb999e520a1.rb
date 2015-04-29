class DNA
  attr_reader :dna_string, :second_string

  def initialize(dna_string)
    @dna_string = dna_string.upcase
  end

  def hamming_distance(second_string)
    @second_string = second_string.upcase
    strings = [dna_string, second_string].sort_by { |string| string.length }
    strings[0].each_char.with_index.reduce(0) do |distance, (char, index)|
      char == strings[1][index] ? distance : distance + 1
    end
  end
end
