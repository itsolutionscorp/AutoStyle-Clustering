class Hamming

  def self.compute(strand1, strand2)

    count  = 0
    
    (0..strand1.length).each do |n|
      count += 1 unless strand1[n] == strand2[n]
    end
          return count
  end

end
