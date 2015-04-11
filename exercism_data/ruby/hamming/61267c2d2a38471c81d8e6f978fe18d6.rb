class Hamming
  def self.compute(a,b)
        
    strand_a = Strand.new(a)
    strand_b = Strand.new(b)

    strand_a.crop!(strand_b)
    strand_b.crop!(strand_a)

    pairs = strand_a.sequence.zip(strand_b.sequence)
    diffs = pairs.map do |a,b|
      if a == b then 0
      else 1
      end
    end
    diffs.inject(:+)
  end        
end    

class Strand
  attr_accessor :sequence

  def initialize(str)
    @sequence = str.chars
  end

  def crop!(strnd)
    if @sequence.length > strnd.sequence.length
      @sequence = @sequence.take(strnd.sequence.length)
      self
    else
      self
    end
  end 
end
