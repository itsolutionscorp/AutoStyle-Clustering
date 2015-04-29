# A module that contains common language analysis functions,
# Which could be useful to any teenager in our times...
module LanguageAnalyzer

  # is the phrase made only of whitespace? (i.e. 'silent')
  def silent?( phrase )
    phrase.nil? || phrase.strip.empty?
  end

  # is the phrase ALL CAPS? (i.e.'yelling')
  def yelling?( phrase )
    !phrase.nil? && phrase.strip.upcase == phrase.strip
  end

  # does the phrase end in a question mark? (i.e. question)
  def question?( phrase )
    !phrase.nil? && ( phrase.end_with? '?' )
  end

end

class Bob

  include LanguageAnalyzer

  def hey( phrase)
    if silent? phrase
      "Fine. Be that way!"
    elsif yelling? phrase
      "Woah, chill out!"
    elsif question? phrase
      "Sure."
    else
      "Whatever."
    end
  end

end
