class Hamming
  def compute(strand, other_strand)
    diff = 0

    [strand.length, other_strand.length].min.times do |ndx|
      diff += 1 if strand[ndx] != other_strand[ndx]
    end

    diff
  end
end
