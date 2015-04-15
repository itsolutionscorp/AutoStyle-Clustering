class Bob

  def hey(message)
    answer(MessageResponseMatcher.new(message))
  end

  def answer(message)
    matchers = [ 'yelling?', 'asking?', 'saying_nothing?', 'default_matcher' ]
    matcher = matchers.find{ |method| message.send(method) }
    select_answer[matcher]
  end

  def select_answer
    {
      'yelling?' => 'Woah, chill out!',
      'asking?' => 'Sure.',
      'saying_nothing?' => 'Fine. Be that way!',
      'default_matcher' => 'Whatever.'
    }
  end
end

class MessageResponseMatcher

  def initialize(message)
    @message = message
  end

  def asking?
    !!(@message =~ /\?$/) && !has_multiple_line?
  end

  def yelling?
    @message == @message.upcase &&
      !!@message.match('[a-zA-Z]')
  end

  def saying_nothing?
    message_without_spaces = @message.strip
    message_without_spaces == ''
  end

  def has_multiple_line?
    !!(@message.match'\n')
  end

  def default_matcher
    'default'
  end
end
