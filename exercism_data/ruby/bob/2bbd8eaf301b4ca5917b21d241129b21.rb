module WordsParser
  def silent?(words)
    words.nil? || words.strip.empty?
  end

  def shout?(words)
    words.upcase == words
  end

  def question?(words)
    words.end_with?("?")
  end
end

class Bob
  include WordsParser

  def hey(words)
    if silent?(words)
      "Fine. Be that way!"
    elsif shout?(words)
      "Woah, chill out!"
    elsif question?(words)
      "Sure."
    else
      "Whatever."
    end
  end
end
