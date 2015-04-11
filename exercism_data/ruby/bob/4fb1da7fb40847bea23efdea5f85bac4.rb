module PhraseAnalyzer
  def question?
    not silence? and end_with? '?'
  end

  def shout?
    self =~ /\w/ && self == upcase
  end

  def silence?
    nil? || empty?
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
