class Teenager

  def hey(words)
    words = words.to_s
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
    words.empty?
  end

  def question?(words)
    words.end_with?('?')
  end
end

class Bob < Teenager

end
