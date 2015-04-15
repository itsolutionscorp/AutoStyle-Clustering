class Hamming
  def self.compute(strand1, strand2)
    [strand1.size, strand2.size].min.times.count { |i| strand1[i] != strand2[i] }
  end
end

## referenced http://codeandkaizen.herokuapp.com/blog/2014/02/05/lessons-in-ruby-from-exercism-dot-io
## for this exercise
