class Proverb
  attr_reader :words
  def initialize(*words)
    @words = words
  end

  def to_s
    i=0
    @proverb = ""
    while i < @words.length-1
      @proverb += "For want of a #{self.words[i]} the #{self.words[i+1]} was lost.\n"
      i+=1
    end

    @proverb += "And all for the want of a #{self.words[0]}."
    @proverb
  end
end
