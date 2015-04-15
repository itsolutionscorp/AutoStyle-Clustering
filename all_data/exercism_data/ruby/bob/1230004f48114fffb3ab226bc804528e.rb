class Bob
  def hey(utterance)
    case
    when shouting?(utterance) then "Woah, chill out!"
    when silent?(utterance)   then "Fine. Be that way!"
    when question?(utterance) then "Sure."
    else "Whatever."
    end
  end

  def shouting?(utterance)
    utterance.upcase == utterance &&
      utterance.downcase != utterance
  end

  def silent?(utterance)
    utterance.strip == ""
  end

  def question?(utterance)
    utterance[-1] == "?"
  end
end
