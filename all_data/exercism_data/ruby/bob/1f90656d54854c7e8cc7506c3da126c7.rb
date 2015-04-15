class Bob
  def hey phrase
    if phrase.strip == ''
      return 'Fine. Be that way!'
    elsif is_shout? phrase
      return 'Woah, chill out!'
    elsif is_question? phrase
      return 'Sure.'
    else
      return 'Whatever.'
    end
  end

  protected
  def is_shout? str
   str =~ /([A-Z][A-Z]+)[, ]*/ && str !~ /([a-z]+)[, ]*/
  end

  def is_question? str
    str =~ /^.*\?\Z/
  end
end
