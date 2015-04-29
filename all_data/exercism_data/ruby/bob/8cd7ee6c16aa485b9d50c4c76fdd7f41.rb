class Bob
  def hey(msg)
    msg = Message.new(msg, response_builder)
    msg.answer
  end

  private
  def shouty_response
    "Woah, chill out!"
  end

  def meh_response
    "Whatever."
  end

  def yesm_response
    "Sure."
  end

  def empty_response
    "Fine. Be that way!"
  end

  def query_response_mapping
    {
      :shout => shouty_response,
      :question => yesm_response,
      :blank => empty_response,
      :default => meh_response
    }
  end

  def response_builder
    Response.new(query_response_mapping)
  end
end

class Response
  def initialize(response_values)
    response_values.each_pair do |k,v|
      self.class.send(:define_method, k, -> { v })
    end
  end
end

class Message
  def initialize(msg, response)
    @msg = msg
    @response = response
  end

  def answer
    k,v = interface.select { |k,v| v }.first
    response.send(k)
  end

  private
  attr_reader :msg, :response

  def all_caps?
    msg == msg.upcase
  end

  def question?
    msg.match /\?\Z/
  end

  def empty?
    msg.strip.empty?
  end

  def interface
    {
      :blank => empty?,
      :shout => all_caps?,
      :question => question?,
      :default => true
    }
  end
end
