class WordsParser
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
  def hey(words)
    @parser = WordsParser.new

    if @parser.silent?(words)
      "Fine. Be that way!"
    elsif @parser.shout?(words)
      "Woah, chill out!"
    elsif @parser.question?(words)
      "Sure."
    else
      "Whatever."
    end
  end
end
