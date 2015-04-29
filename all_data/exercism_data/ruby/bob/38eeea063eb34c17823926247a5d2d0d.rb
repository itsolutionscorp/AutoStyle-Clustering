class Bob

  def hey(greeting)
    prompt = Prompt.new(greeting)

    if prompt.nonverbal?
      "Fine. Be that way."
    elsif prompt.startling?
      "Woah, chill out!"
    elsif prompt.inquisitive?
      "Sure."
    else
      "Whatever."
    end
  end

end

class Prompt

  def initialize(content)
    @content = String(content)
  end

  def nonverbal?
    @content.empty?
  end

  def inquisitive?
    @content.end_with?('?')
  end

  def startling?
    @content.upcase == @content
  end

end
