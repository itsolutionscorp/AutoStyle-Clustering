class Bob

  def hey(words=nil)
    @words = words
    answer = 'Whatever.'
    {:question? => 'Sure.', :yell? => 'Woah, chill out!', :nothing? => 'Fine. Be that way!'}.each do |m, a|
      answer = a if self.send(m)
    end
    answer
  end

  private
  def yell?
    @words == @words.upcase
  end

  def nothing?
    @words.empty? || @words.delete(" ").length == 0
  end

  def question?
    @words[-1, 1] == '?'
  end

end
