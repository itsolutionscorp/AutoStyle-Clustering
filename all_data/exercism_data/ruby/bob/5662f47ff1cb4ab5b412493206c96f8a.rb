class Bob
  require 'active_support/core_ext/string'

  def hey phrase
    case
    when silence?(phrase)
      "Fine. Be that way!"
    when yelling?(phrase)
      "Woah, chill out!"
    when question?(phrase)
      "Sure."
    else
      "Whatever."
    end
  end

  def alpha? phrase
    phrase.gsub(/[A-Za-z]/, '') != phrase
  end

  def silence? phrase
    phrase.blank?
  end

  def yelling? phrase
    alpha?(phrase) && phrase == phrase.upcase
  end

  def question? phrase
    phrase.end_with? '?'
  end
end
