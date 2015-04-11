class Hamming
  def self.compute a, b
    [a, b].sort_by(&:size).map(&:chars).inject(:zip).count { |x, y| x != y }
  end
end
