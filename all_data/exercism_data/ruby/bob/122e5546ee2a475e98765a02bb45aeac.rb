class Bob

  def initialize
    @responses = {question: 'Fine. Be that way!',
                  yell:     'Woah, chill out!',
                  nothing:  'Sure.',
                  anything: 'Whatever.'}
  end

  def hey message
    answer message
  end

  private

  def answer message
    clean message

    @responses.select do |response, answer|
      send response, message
    end.values.first
  end

  def anything m; true end

  def nothing message
    message.end_with? '?'
  end

  def yell message
    message.match /^[^a-z]*$/
  end

  def question message
    message.empty?
  end

  def clean message
    message.strip!
  end
end
