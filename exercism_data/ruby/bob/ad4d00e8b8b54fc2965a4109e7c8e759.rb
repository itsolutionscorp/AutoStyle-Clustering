class Bob

  DEFAULT_ANSWER = "Whatever."
  SHOUT_ANSWER = "Woah, chill out!"
  QUESTION_ANSWER = "Sure."
  SILENCE_ANSWER = "Fine. Be that way!"

  IS_BLANK = ->(phrase) { phrase.strip.size == 0 }
  IS_SHOUT = ->(phrase) do
    p = phrase.gsub(/\d/, "")
    p != p.downcase && p == p.upcase
  end
  IS_QUESTION = ->(phrase) do
    phrase =~ /\?\Z/m
  end

  def hey(phrase)
    case phrase.gsub(/\d/,"")
      when IS_BLANK then SILENCE_ANSWER
      when IS_SHOUT then SHOUT_ANSWER
      when IS_QUESTION then  QUESTION_ANSWER
      else
        DEFAULT_ANSWER
    end
  end

end
