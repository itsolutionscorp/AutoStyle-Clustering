class Complement
  @dna_nucleotides = ['A', 'C', 'G', 'T']
  @rna_nucleotides = ['A', 'C', 'G', 'U']

  def self.of_dna(input)
    transpose(@dna_nucleotides, @rna_nucleotides, input)
  end

  def self.of_rna(input)
    transpose(@rna_nucleotides, @dna_nucleotides, input)
  end

  private
    def self.transpose(source_array, target_array, input)
      result = ""
      input.each_char do |item|
        index = 3 - source_array.find_index(item)
        result << target_array[index]
      end

      result
    end
end
