require 'pry'
class Proverb
  def initialize(*chain)
    @chain = chain 
    if @chain.last.is_a? Hash
      @qualifier = @chain.pop[:qualifier]
    else
      @qualifier = nil
    end
  end

  def to_s
    mid_lines + last_line
  end 

  private

  def mid_lines
    @chain.each_cons(2).map do |word1, word2|
       mid_line(word1,word2)
    end.join
  end

  def mid_line(word1, word2)
    "For want of a #{word1} the #{word2} was lost.\n"
  end

  def last_line
    "And all for the want of a #{qualifier}#{@chain[0]}."
  end

  def qualifier
    @qualifier.nil? ? "" : "#{@qualifier} " 
  end

end
