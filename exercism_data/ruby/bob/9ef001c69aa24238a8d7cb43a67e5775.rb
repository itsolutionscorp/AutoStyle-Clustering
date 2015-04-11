class String
  def all_upcase?
    utf_pattern = Regexp.new("\\p{Lower}".force_encoding("UTF-8"))
    self.match(utf_pattern).nil?
  end

  def blank?
    self.strip == ''
  end

  def is_question?
    self[-1] == '?'
  end
end

class Bob
  def hey(say)
    return 'Fine. Be that way!' if say.blank?
    return 'Woah, chill out!' if say.all_upcase?
    return 'Sure.' if say.is_question?

    'Whatever.'
  end

end
