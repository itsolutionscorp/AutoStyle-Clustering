class Bob
  attr_reader :shouter, :questioner, :silencer, :responses

  VERBS = %i(shout question silence)

  def initialize
    @shouter = /(?<shout>(^(\w[^a-z])+(\W|\d|[A-Z])))/
    @questioner = /(?<question>\?\z)/
    @silencer = /(?<silence>(\s[^\S])|(^\z))/
  end

  def hey(conversation)
    match_responses(conversation)
    answer
  end

  def match_responses(conversation)
    @responses = /(#{shouter}|#{questioner}|#{silencer})/.match(conversation)
  end

  def answer
    send(response_type)
  end

  def response_type
    responses ? best_response : :whatever
  end

  def best_response
    VERBS[first_match]
  end

  def first_match
    responses.to_a[1..-1].find_index { |v| v }
  end

  def shout
    'Woah, chill out!'
  end

  def question
    'Sure.'
  end

  def silence
    'Fine. Be that way!'
  end

  def whatever
    'Whatever.'
  end

end
