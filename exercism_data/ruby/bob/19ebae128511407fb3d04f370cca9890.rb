class Bob
  def hey(utterance)
    utterance = utterance.to_s
    if nothing_to_say_to_me?(utterance)
      "Fine. Be that way!"
    elsif yelling_at_me?(utterance)
      "Woah, chill out!"
    elsif want_something_from_me?(utterance)
      "Sure."
    else
      "Whatever."
    end
  end

  def nothing_to_say_to_me?(utterance)
    utterance.empty?
  end

  def yelling_at_me?(utterance)
    utterance.upcase == utterance
  end

  def want_something_from_me?(utterance)
    utterance.chars.last == "?"
  end
end
