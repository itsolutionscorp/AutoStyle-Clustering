class Response
  def surly
    'Fine. Be that way!'
  end

  def grumpy
    'Woah, chill out!'
  end

  def sure
    'Sure.'
  end

  def whatever
    'Whatever.'
  end
end

class Bob
  def initialize(response=Response.new)
    @response = response
  end

  def hey(msg)
    case kill_newlines(msg)
    when silence then  response.surly
    when shouting then response.grumpy
    when question then response.sure
    else response.whatever
    end
  end

  private
  attr_reader :response

  def silence
    ->(msg){ msg.strip.empty? }
  end

  def shouting
    ->(msg){ msg == msg.upcase }
  end

  def question
    ->(msg){ msg.end_with? '?' }
  end

  def kill_newlines msg
    msg.gsub("\n", '')
  end
end
