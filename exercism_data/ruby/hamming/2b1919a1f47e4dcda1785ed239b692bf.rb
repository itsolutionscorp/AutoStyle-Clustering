class Hamming
  # Check the previous version for a readable multi-line submission
  def self.compute a, b
    [a, b].map(&:length).min.times.count{|i| a[i] != b[i]}
  end
end
