class Bob

  def hey(sentence)
    case
    when is_silence?(sentence) then 'Fine. Be that way!'
    when is_shout?(sentence) then 'Woah, chill out!'
    when is_question?(sentence) then 'Sure.'
    else 'Whatever.'
    end
  end

  private
  def is_question?(sentence)
    sentence[-1] == '?'
  end

  def is_shout?(sentence)
    sentence =~ /[a-bA-Z]/ && sentence == sentence.upcase
  end

  def is_silence?(sentence)
    sentence.strip.empty?
  end
end
