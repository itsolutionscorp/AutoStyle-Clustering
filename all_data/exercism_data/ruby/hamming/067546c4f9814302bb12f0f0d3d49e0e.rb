class Hamming
  def self.compute(a,b)
    diff = 0
    counter = 0
    min_length = [a.length, b.length].min
    while counter <= min_length - 1
      if a[counter] != b[counter]
        diff +=1
      end
    counter +=1
    end
    diff
  end
end
