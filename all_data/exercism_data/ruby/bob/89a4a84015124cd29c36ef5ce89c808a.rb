class WordsParser
  
  def initialize(words)
    @words = words
  end
  
  def silent?
    @words.to_s.strip.empty?
  end

  def shout?
    @words.upcase == @words
  end

  def question?
    @words.end_with?("?")
  end
end

class Bob
  def hey(words)
    @parser = WordsParser.new(words)

    if @parser.silent?
      "Fine. Be that way!"
    elsif @parser.shout?
      "Woah, chill out!"
    elsif @parser.question?
      "Sure."
    else
      "Whatever."
    end
  end
end
