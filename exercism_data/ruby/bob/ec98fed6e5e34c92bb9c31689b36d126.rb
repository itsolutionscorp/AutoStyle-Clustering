# A module that contains common language analysis functions,
# Which could be useful to any teenager in our times...
module LanguageAnalyzer

  # is the phrase made only of whitespace? (i.e. 'silent')
  def self.silent?( phrase )
    phrase.strip.empty?
  end

  # is the phrase ALL CAPS? (i.e.'yelling')
  def self.yelling?( phrase )
    phrase.strip.upcase == phrase.strip
  end

  # does the phrase end in a question mark? (i.e. question)
  def self.question?( phrase )
    phrase[-1] == '?'
  end

end

class Bob

  include LanguageAnalyzer

  def hey( phrase)
    if LanguageAnalyzer::silent?( phrase )
      "Fine. Be that way!"
    elsif LanguageAnalyzer::yelling?( phrase )
      "Woah, chill out!"
    elsif LanguageAnalyzer::question?( phrase )
      "Sure."
    else
      "Whatever."
    end
  end

end
