class Hamming
  def self.compute(a,b)
    counter = 0
    (0..(a.length-1)).each do |index|
      if a[index] != b[index]
        counter += 1
      end
    end
    counter
  end
end
