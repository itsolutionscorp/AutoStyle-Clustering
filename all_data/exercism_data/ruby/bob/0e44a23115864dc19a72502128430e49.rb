class Bob
  def hey(input)
    case
    when input.blank?
      'Fine. Be that way!'
    when input.all_caps?
      'Woah, chill out!'
    when input.a_question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end
class String
  def blank?
    self.strip.empty?
  end
  def all_caps?
    !!self.match(/\A([^[a-z]]+)\z/) && !!self.match(/[A-Z]+/)
  end
  def a_question?
    self.end_with? '?'
  end
end
