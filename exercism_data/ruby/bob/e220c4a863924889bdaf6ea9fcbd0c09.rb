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
    when silence then  surly_response
    when shouting then grumpy_response
    when question then sure_response
    else whatever_response
    end
  end

  private
  attr_reader :response

  def silence
    ->(msg){ msg.match(/^\s*$/) }
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

  def method_missing(meth, *args)
    if meth.to_s.end_with?('_response')
      adjective = meth.to_s.split('_').first
      response.public_send(adjective)
    else
      super
    end
  end

  def respond_to_missing(meth, include_private = false)
    meth.to_s.end_with?('_response') || super
  end
end
