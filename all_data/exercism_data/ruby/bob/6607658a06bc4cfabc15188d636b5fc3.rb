class Bob
  require 'active_support/core_ext/string'
  def hey phrase
    case
    when is_silence?(phrase)
      "Fine. Be that way!"
    when is_yelling?(phrase)
      "Woah, chill out!"
    when is_question?(phrase)
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def is_alpha? phrase
    phrase.gsub(/[A-Za-z]/, '') != phrase
  end

  def is_silence? phrase
    phrase.blank? || phrase.nil?
  end

  def is_yelling? phrase
    is_alpha?(phrase) && phrase == phrase.upcase
  end

  def is_question? phrase
    phrase.end_with? '?'
  end
end
