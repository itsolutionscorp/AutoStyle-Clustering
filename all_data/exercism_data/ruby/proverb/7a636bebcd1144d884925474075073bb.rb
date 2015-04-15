class Proverb
  attr_reader :chain, :qualifier

  def initialize(*chain)
    @qualifier = chain.last.is_a?(Hash) ? chain.pop : {} 
    @chain = chain
    @close = @qualifier.empty? ? @chain.first : "#{@qualifier[:qualifier]} #{@chain.first}"
  end

  def to_s
    text = []
    chain.each_cons(2).map do |word1, word2|
      text << cause(word1,word2)
    end
    text << closing(@close)
    text.join('')
  end

  def cause (word1, word2)
    "For want of a #{word1} the #{word2} was lost.\n"
  end

  def closing close 
    "And all for the want of a #{close}."
  end

end
