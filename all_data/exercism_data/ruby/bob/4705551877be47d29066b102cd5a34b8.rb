class Bob

  def hey(words)
    return "Fine. Be that way!" if words.nil? || words.strip.size == 0
    yell = (words.upcase == words)
    question = (words.end_with? '?')
    statement = (words.end_with? '.')
    
    tone = { :yell => yell, :question => question, :statement => statement }
    
    case tone.select { |k, v| v == true }.keys[0]
    when :yell then "Woah, chill out!"
    when :question then "Sure."
    else
      "Whatever."
    end
  end
  
end
