module PhraseAnalyzer
  def question?
    end_with? '?'
  end

  def shout?
    self == upcase
  end

  def silence?
    to_s.empty?
  end
end

class Bob
  def hey(phrase)
    phrase.extend PhraseAnalyzer

    if phrase.silence?
      'Fine. Be that way.'
    elsif phrase.shout?
      'Woah, chill out!'
    elsif phrase.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end
