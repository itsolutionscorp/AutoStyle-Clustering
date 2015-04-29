class Teenager

  def hey(words)
    words = words.to_s
    case
    when nothing?(words)
      'Fine. Be that way!'
    when shout?(words)
      'Woah, chill out!'
    when question?(words)
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
