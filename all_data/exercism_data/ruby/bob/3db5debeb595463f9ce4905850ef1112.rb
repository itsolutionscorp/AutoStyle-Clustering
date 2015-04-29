class Teenager

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
    words.nil? || words.empty?
  end

  def question?(words)
    words.end_with?('?')
  end
end

class Bob < Teenager

end
