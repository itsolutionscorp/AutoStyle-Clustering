class Nucleotide

  def self.from_dna(str)
    str.chars
  end

end

class Array
  def histogram
    hash = {'A'=> 0, 'T'=> 0, 'C'=> 0, 'G'=>0 }
    self.inject(hash) { |sum, val| sum[val] += 1; sum }
  end
end
