class String

  def question_asked?
    self.end_with? '?'
  end

  def is_all_caps?
    self == self.upcase
  end

end

class Bob

  def hey(str)
    return 'Fine. Be that way.' if str.nil? or str.empty?
    return 'Woah, chill out!' if str.is_all_caps?
    return 'Sure.' if str.question_asked?
    'Whatever.'
  end

end
