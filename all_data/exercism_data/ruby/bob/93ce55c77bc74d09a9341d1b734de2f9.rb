class Bob

  def hey text
    clean_text = text.strip

    if clean_text.is_shouting?
      shout
    elsif clean_text.is_question?
      question
    elsif clean_text.empty?
      empty
    else
      whatever
    end
  end

  def empty
    'Fine. Be that way!'
  end

  def whatever
    'Whatever.'
  end

  def shout
    'Whoa, chill out!'
  end

  def question
    'Sure.'
  end
end

class String

  def is_shouting?
    includes_large_caps? && !includes_small_caps?
  end

  def is_question?
    self[-1] == '?'
  end

  def includes_large_caps?
    /[A-Z]/ =~ self
  end

  def includes_small_caps?
    /[a-z]/ =~ self
  end
end
