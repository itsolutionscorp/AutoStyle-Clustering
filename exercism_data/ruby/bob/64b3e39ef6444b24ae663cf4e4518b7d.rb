class String
  def all_upcase?
    utf_pattern = Regexp.new("\\p{Lower}".force_encoding("UTF-8"))
    self.match(utf_pattern).nil?
  end
end

class Bob
  def hey(say)
    return 'Fine. Be that way!' if say.strip == ''
    return 'Woah, chill out!' if say.all_upcase?
    return 'Sure.' if say[-1] == '?'

    'Whatever.'
  end

end
