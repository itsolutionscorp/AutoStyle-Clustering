class DNA < Struct.new(:sequence)
  def hamming_distance(other_sequence)
    sequence.chars.select.with_index do |base, i|
      other_sequence[i] && other_sequence[i] != base
    end.count
  end
end
