class Bob

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

  private

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
