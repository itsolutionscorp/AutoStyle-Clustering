class Bob
  def hey(words)
    conversation = Conversation.new(words)
    response = Response.new(conversation)
    response.respond
  end
end

class Conversation
  attr_reader :words
  def initialize(words)
    @words = words
  end
  def silent
    ''
  end
  def shouting
    @words.upcase
  end
  def asking_questions
    /\?$/
  end
end

class Response
  attr_reader :conversation
  def initialize(conversation = nil)
    @conversation = conversation
  end
  def respond
    case conversation.words.to_s
    when conversation.silent
      'Fine. Be that way!'
    when conversation.shouting
      'Woah, chill out!'
    when conversation.asking_questions
      'Sure.'
    else
      'Whatever.'
    end
  end
end
