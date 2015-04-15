class Bob
  def hey phrase
    @phrase = phrase.to_s.strip
    if silence?
      'Fine. Be that way!'
    elsif scream?
      'Woah, chill out!'
    elsif question?
      'Sure.'
    else
      'Whatever.'
    end
  end

private

  def silence?
    @phrase.empty?
  end

  def scream?
    @phrase.upcase == @phrase
  end

  def question?
    @phrase.end_with? '?'
  end
end
