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
    case
      when say.blank? then 'Fine. Be that way!'
      when say.all_upcase? then 'Woah, chill out!'
      when say.is_question? then 'Sure.'
      else 'Whatever.'
    end
  end

end
