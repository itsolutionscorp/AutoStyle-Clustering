class Hamming
  def self.compute(a, b)
    shorter, longer = [a, b].sort_by(&:length)
    shorter.chars.select.with_index do |char, i|
      char != longer[i]
    end.size
  end
end
