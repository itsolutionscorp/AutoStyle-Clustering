class Hamming
  def self.compute(first, last)
    short, long = [first.chars, last.chars].sort_by!(&:size)
    short.zip(long).count { |a,b| a != b }
  end
end
