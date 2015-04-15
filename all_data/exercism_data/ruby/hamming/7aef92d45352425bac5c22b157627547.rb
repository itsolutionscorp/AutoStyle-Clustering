class Hamming
  def self.compute(source, target)
    return Hamming.compute(target, source) if source.size > target.size

    (0...source.size).count {|i| source[i] != target[i] }
  end
end
