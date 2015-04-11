class Bob
  
  def hey(what)
    if not_a_question?(what)
      'Fine. Be that way.'
    else
      # All tests in this group assume that what is non-empty string
      if question?(what)
      'Sure.'
      elsif yell?(what)
      'Woah, chill out!'
      else
      'Whatever.'
      end
    end
  end

  private

  def question?(what)
    what =~ /\?$/
  end

  def yell?(what)
    !(what =~ /[a-z]/)
  end

  def not_a_question?(what)
    (what == nil) or (what == '')
  end
end
