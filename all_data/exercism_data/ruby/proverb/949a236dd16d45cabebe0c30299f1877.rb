class Proverb
  attr_reader :words

  def initialize(*words, qualifier: '')
    @words = words
    @qualifier = qualifier
  end

  def to_s
    proverb = String.new
    for x in 0...@words.length
      proverb += "For want of a " + @words[x] + " the " + @words[x + 1] + " was lost.\n" unless @words[x + 1].nil?
    end
    qualifier = @qualifier + ' ' unless @qualifier == ''
    proverb += "And all for the want of a #{qualifier}" + @words[0] + "."
  end
end
