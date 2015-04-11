class Hamming

  def self.compute(a,b)
    length = ([a.size , b.size].min) - 1
    (0..length).inject(0) { |distance, i| a[i] != b[i] ? distance += 1 : distance }
  end
end
