class Bob

  def hey(text)
    case text
    when shout?(text) then 'Woah, chill out!'
    when question?(text) then 'Sure.'
    when spaces_only?(text)  then'Fine. Be that way!'
    else 'Whatever.'
    end
  end

  def shout?(text)
    text[/[^a-z]*[A-Z][^a-z]*/]
  end

  def question?(text)
    text[/[^\?]*\?\z/]
  end

  def spaces_only?(text)
    text[/\A\s*\z/]
  end

end
