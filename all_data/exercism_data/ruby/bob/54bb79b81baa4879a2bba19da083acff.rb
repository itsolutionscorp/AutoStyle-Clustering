class Bob

  def hey(message)
    answer(MessageResponseMatcher.new(message))
  end

  def answer(message)
    matchers = [ 'yelling?', 'asking?', 'saying_nothing?', 'default_matcher' ]
    matcher = matchers.find_index{ |method| message.public_send(method) }
    select_answer[matcher]
  end

  def select_answer
    [ 'Woah, chill out!', 'Sure.', 'Fine. Be that way!', 'Whatever.' ]
  end
end

class MessageResponseMatcher

  def initialize(message)
    @message = message
  end

  def asking?
    @message =~ (/\?\z/)
  end

  def yelling?
    @message == @message.upcase && @message =~ (/[a-zA-Z]/)
  end

  def saying_nothing?
    @message.strip == ''
  end

  def default_matcher
    'default'
  end
end
