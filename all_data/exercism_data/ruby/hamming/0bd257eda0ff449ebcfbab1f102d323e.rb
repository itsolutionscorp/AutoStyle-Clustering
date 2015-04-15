class Hamming

  def self.compute(a,b)

    length = ([a.size , b.size].min) - 1

    (0..length).select { |i| a[i] != b[i] }.size

  end

end
