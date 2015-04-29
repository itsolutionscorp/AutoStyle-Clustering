class Bob
  def hey(text)
    statement = Message.new(text)
      return 'Fine. Be that way!' if statement.silent?
      return 'Woah, chill out!' if statement.shouting?
      return 'Sure.' if statement.question?
      return 'Whatever.'
  end
end

class Message
  def initialize(text)
    @text = text.to_s.strip
  end
  def silent?
    @text.empty?
  end
  def shouting?
    @text == @text.upcase
  end
  def question?
    @text.end_with?("?")
  end
end
