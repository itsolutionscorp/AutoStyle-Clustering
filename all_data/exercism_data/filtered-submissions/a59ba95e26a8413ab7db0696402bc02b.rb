class DNA < Struct.new(:sequence)
  def hamming_distance(dna_sequence)
    sequence.chars.select.with_index do |base, i|
      dna_sequence.length > i && dna_sequence[i] != base
    end.count
  end
end
