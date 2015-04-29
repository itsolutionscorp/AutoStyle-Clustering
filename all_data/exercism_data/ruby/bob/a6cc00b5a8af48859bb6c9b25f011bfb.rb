class Bob

  def hey( sentence )
    case
      when empty?(sentence) then "Fine. Be that way!"
      when shout?(sentence) then "Woah, chill out!"
      when question?(sentence) then "Sure."
      else "Whatever."
    end
  end

private

  def empty?( sentence )
    sentence.strip.empty?
  end

  def shout?( sentence )
    sentence.upcase == sentence && sentence.downcase != sentence
  end

  def question?( sentence )
    sentence.end_with? '?'
  end

end
