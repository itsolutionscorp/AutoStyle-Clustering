class String
  def is_silence?
    self =~ /\A\s*\Z$/
  end

  def is_shouting?
    all_letters_uppercase?
  end

  def is_question?
    end_with? '?'
  end

  def all_letters_uppercase?
    letters = scan(/[a-z]/i)
    letters.any? && letters.all? {|l| l.upcase == l }
  end
end

class Bob
  def hey phrase
    return 'Fine. Be that way!' if phrase.is_silence?
    return 'Whoa, chill out!' if phrase.is_shouting?
    return 'Sure.' if phrase.is_question?
    'Whatever.'
  end
end