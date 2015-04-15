class Bob

  def hey(words)
    type = [:shouting, :question, :silence].find { |method| send "#{method}?", words } || :other
    send("respond_to_#{type}")
  end

  private

  def respond_to_other
    'Whatever.'
  end

  def respond_to_question
    'Sure.'
  end

  def respond_to_silence
    'Fine. Be that way!'
  end

  def respond_to_shouting
    'Woah, chill out!'
  end

  def shouting?(words)
    !words.chars.any? { |c| ('a'..'z').cover?(c) } && words.chars.any? { |c| ('a'..'z').cover?(c.downcase) }
  end

  def silence?(words)
    words.strip.empty?
  end

  def question?(words)
    words.chars.last == '?'
  end

end
