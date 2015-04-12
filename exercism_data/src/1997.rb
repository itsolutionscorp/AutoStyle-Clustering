def compute original, mutated
    pairs = original.chars.zip mutated.chars
    pairs.delete_if do |pair|
      pair.first.nil? or pair.last.nil? or pair.first == pair.last
    end.count
  end