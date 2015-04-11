class Bob
  TRANSLATION_TABLE = {
    "Fine. Be that way!" => lambda { |words| !words || words.lstrip.empty? },
    "Woah, chill out!"   => lambda { |words| words == words.upcase },
    "Sure."              => lambda { |words| words[-1] == "?" }
  }
  def hey(words)
    TRANSLATION_TABLE.each_pair  do |response, test|
      return response if test.call(words)
    end
    "Whatever."
  end

end
