def compute original, mutated
    pairs = original.split('').zip mutated.split('')
    pairs.delete_if do |pair|
      pair.first.nil? or pair.last.nil? or pair.first == pair.last
    end.count
  end