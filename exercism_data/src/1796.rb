class Hamming
  def compute(strand1, strand2)
    ham_dist = 0
    if strand1.length == strand2.length
      strand1.length.times do |x|
        if strand1[x] != strand2[x]
          ham_dist += 1
        end
        x += 1
      end
    end
    ham_dist
  end

end
