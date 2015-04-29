class Bob
  def hey words
    conversation = Conversation.new(words)
    conversation.respond
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
  def respond 
    case @words.to_s
    when silent?
      'Fine. Be that way!'
    when shouting?
      'Woah, chill out!'
    when asking_questions?
      'Sure.'
    else
      'Whatever.'
    end
  end
end
