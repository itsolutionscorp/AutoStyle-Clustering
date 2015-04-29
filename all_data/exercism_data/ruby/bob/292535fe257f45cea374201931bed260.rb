class String
  def contains_lowercase?
    self[/[[:lower:]]/]
  end

  def contains_uppercase?
    self[/[[:upper:]]/]
  end
end

class Bob

  def hey(sentence)
    case
      when empty?(sentence) then "Fine. Be that way!"
      when shout?(sentence) then "Woah, chill out!"
      when question?(sentence) then "Sure."
      else "Whatever."
    end
  end

private

  def empty?(sentence)
    sentence.strip.empty?
  end

  def shout?(sentence)
    sentence.contains_uppercase? && ! sentence.contains_lowercase?
  end

  def question?(sentence)
    sentence.end_with? '?'
  end

end
