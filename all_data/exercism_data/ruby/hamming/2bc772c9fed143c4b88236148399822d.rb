class Hamming

  def self.compute(strand1, strand2)
    dist = 0
    strand1.size.times do |index|
      dist += different?(strand1[index], strand2[index])
    end
    return dist
  end

  protected

  def self.different?(letter1, letter2)
    letter1 != letter2 ? 1 : 0
  end

end
