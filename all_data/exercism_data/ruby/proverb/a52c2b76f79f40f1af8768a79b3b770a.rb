require 'pry'
class Proverb
  def initialize(*chain)
    @chain = *chain 
    if @chain.last.class == Hash
      @qualifier = @chain.pop[:qualifier]
    else
      @qualifier = nil
    end
  end

  def to_s
    string = ""
    (1...@chain.length).each do |i|
      string << line(@chain[i-1], @chain[i])
    end
    string << last_line(@chain[0])
  end 

  private

  def line(word1, word2)
    "For want of a #{word1} the #{word2} was lost.\n"
  end

  def last_line(word)
    "And all for the want of a #{qualifier}#{word}."
  end

  def qualifier
    @qualifier.nil? ? "" : "#{@qualifier} " 
  end

end
