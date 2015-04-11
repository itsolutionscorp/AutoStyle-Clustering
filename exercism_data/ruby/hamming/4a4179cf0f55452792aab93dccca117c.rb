class Hamming
  def self.compute(a, b)
    count = 0
    min_length = [a.length, b.length].min
    min_length.times do |i|
      count +=1 if a[i] != b[i]
    end

    count
  end
end
