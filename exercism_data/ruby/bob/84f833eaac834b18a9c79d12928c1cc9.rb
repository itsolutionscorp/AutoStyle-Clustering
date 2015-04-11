class Bob
  def hey(words)
    conversation = Conversation.new(words)
    response = Response.new(words,conversation)
    response.respond
  end
end

class Conversation
  def initialize(words)
    @words = words
  end
  def silent?
    ''
  end
  def shouting?
    @words.upcase
  end
  def asking_questions?
    /\?$/
  end
end

class Response
  attr_reader :words,:conversation
  def initialize(words,conversation = nil)
    @words = words
    @conversation = conversation
  end
  def respond
    case words.to_s
    when conversation.silent?
      'Fine. Be that way!'
    when conversation.shouting?
      'Woah, chill out!'
    when conversation.asking_questions?
      'Sure.'
    else
      'Whatever.'
    end
  end
end
