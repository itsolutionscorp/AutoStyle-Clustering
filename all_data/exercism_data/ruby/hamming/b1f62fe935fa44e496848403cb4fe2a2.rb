class Hamming
  def self.compute(strand_one, strand_two)
    strand_one.chars.zip(strand_two.chars).count do |one, two|
      self.matches?(one, two)
    end
  end

  private

  def self.matches?(one, two)
    (one != two) && (two !=nil)
  end
end
