class Bob
  def hey(input)
    RESPONSES.find {|k,v| send(k, input) }.last
  end

  private

  RESPONSES = {
    :shouting? => "Woah, chill out!",
    :question? => "Sure.",
    :silence?  => "Fine. Be that way!",
    :default   => "Whatever."
  }

  def shouting?(input)
    letters = letters_only(input)
    letters.any? && (letters == letters.map(&:upcase))
  end

  def letters_only(input)
    input.scan(/[a-zA-Z]/)
  end

  def question?(input)
    input.chars.last == "?"
  end

  def silence?(input)
    input.strip.empty?
  end

  def default(*)
    true
  end
end
