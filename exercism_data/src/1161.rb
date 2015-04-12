class Hamming
  def compute(source, target)
    return 0 if source == target
    return Hamming.compute(target, source) if source.size > target.size

    distance = 0
    for i in 0...source.size
      distance += 1 unless source[i] == target[i]
    end
    distance
  end
end
