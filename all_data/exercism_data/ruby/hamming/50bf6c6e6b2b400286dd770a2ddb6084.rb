class Hamming
  def self.compute a, b
    shortest, longest = character_arrays_by_length(a, b)
    shortest.zip(longest).select {|a,b| a != b}.length
  end

  def self.string_by_length a, b
    [a.chars.to_a, b.chars.to_a].sort_by(&:length)
  end
end
