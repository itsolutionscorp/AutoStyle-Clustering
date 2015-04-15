class Bob
  TRANSLATION_TABLE = {
    "Fine. Be that way!" => lambda { |words| !words || words.lstrip.empty? },
    "Woah, chill out!"   => lambda { |words| words == words.upcase },
    "Sure."              => lambda { |words| words[-1] == "?" },
    "Whatever."          => lambda { |words| true }
  }
  def hey(words)
    TRANSLATION_TABLE.detect do |response, test|
      test === words
    end.first
  end

end
