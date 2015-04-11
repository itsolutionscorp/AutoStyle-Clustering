class Hamming
  # version 1
  def self.compute(dna1, dna2)
    dna1.chars.zip(dna2.chars).count { |pair| pair.first != pair.last }
  end

  # version 2
  # def self.compute(a,b)
  #   a.size.times.inject(0) { |sum, i| sum += (a[i] <=> b[i]).abs }
  # end

  # version 3
  # def self.compute(a,b)
  #   a.chars.count - (uniquify(a) & uniquify(b)).count
  # end

  # def self.uniquify(string)
  #   string.chars.each_with_index.map { |char,i| char + i.to_s }
  # end
  
end
