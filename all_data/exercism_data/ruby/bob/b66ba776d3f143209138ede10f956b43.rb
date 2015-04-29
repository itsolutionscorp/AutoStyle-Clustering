class Bob

  def hey(words)
    if nothing?(words)
      'Fine. Be that way!'
    elsif shout?(words)
      'Woah, chill out!'
    elsif question?(words)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private
  def shout?(words)
    words == words.upcase
  end

  def nothing?(words)
    words.to_s == ''
  end

  def question?(words)
    words.end_with?('?')
  end
end
