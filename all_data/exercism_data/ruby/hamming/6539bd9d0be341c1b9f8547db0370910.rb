class Hamming

  def self.compute(a, b)
    count = 0
    length = set_length(a, b)
    (0..length).each do |n|
      count +=1 if a[n] != b[n]
    end
    count
  end

  def self.set_length(a, b)
    if a <= b
      length = a.length - 1
    else
      length = b.length - 1
    end
  end
end
