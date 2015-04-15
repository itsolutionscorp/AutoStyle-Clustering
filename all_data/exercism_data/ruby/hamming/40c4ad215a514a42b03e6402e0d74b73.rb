class Hamming
  def self.compute(nucleotide, nucleotide_copy)
    count_mutations(nucleotide, nucleotide_copy)
  end

  private
  def self.count_mutations(nucleotide, nucleotide_copy)
    counter = 0
    nucleotide.length.times do |position|
      return counter if position == nucleotide_copy.length
      unless nucleotide[position] == nucleotide_copy[position]
        counter += 1
      end
    end
    counter
  end

end
