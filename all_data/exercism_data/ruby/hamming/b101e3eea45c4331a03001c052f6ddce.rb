class Hamming
  def self.compute(strand1, strand2)
    (0..strand1.length).inject(0) do | diff, index |
      strand1[index] == strand2[index] ? diff : diff += 1 
    end
  end
  
end
