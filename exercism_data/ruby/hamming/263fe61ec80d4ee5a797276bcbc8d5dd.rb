class Hamming

  def self.compute(a,b)
    length = ([a.size , b.size].min) - 1
    (0..length).inject(0) { |distance, i| distance += 1 if a[i] != b[i]; distance }
  end
end
