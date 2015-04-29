class Bob

  def initialize
    @responses = {question: 'Fine. Be that way!',
                  yell:     'Woah, chill out!',
                  nothing:  'Sure.',
                  anything: 'Whatever.'}
  end

  def hey message
    answer meaning message
  end

  private

  def answer response
    @responses[response]
  end

  def meaning message
    clean message

    if message.empty?
      :question
    elsif message.match /^[^a-z]*$/
      :yell
    elsif message.end_with? '?'
      :nothing
    else
      :anything
    end
  end

  def clean message
    message.strip!
  end
end
