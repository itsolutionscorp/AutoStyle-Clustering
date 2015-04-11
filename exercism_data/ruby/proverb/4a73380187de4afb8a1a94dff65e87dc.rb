class Proverb
  attr_reader :words
  def initialize(*words)
    @words = words
    if @words[-1].class == Hash
      @qualifier = @words[-1]
      @words.pop
    end
  end

  def to_s
    i=0
    @proverb = ""
    while i < @words.length-1
      @proverb += "For want of a #{self.words[i]} the #{self.words[i+1]} was lost.\n"
      i+=1
    end

    if @qualifier
      @proverb += "And all for the want of a #{@qualifier[:qualifier]} #{self.words[0]}."
    else
      @proverb += "And all for the want of a #{self.words[0]}."
    end

    @proverb
  end
end
