# Hamming distance calculator
#   Alex Standke, October 2014

class Hamming
  def self.compute a, b
    [a,b].map(&:size).min.times.count do |x|
      a[x] != b[x]
    end
  end
end
