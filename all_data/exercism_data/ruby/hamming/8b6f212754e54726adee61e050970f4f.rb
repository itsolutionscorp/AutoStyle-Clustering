class Hamming
  def self.compute(first_strand, second_strand)
    first_strand.chars.zip(second_strand.chars).each.inject(0) do |differences, pair|
      pair.first == pair.last ? differences : differences + 1
    end
  end
end
