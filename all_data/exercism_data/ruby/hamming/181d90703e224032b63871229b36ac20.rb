class Hamming

  def self.compute(strand_one, strand_two)
    strand_one.split('').zip(strand_two.split('')).select do |one,two|
      two && one != two
    end.count
  end

end
