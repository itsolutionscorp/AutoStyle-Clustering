require 'delegate'

# Decorates String with conversation-relevant query methods
class ConversationalString < SimpleDelegator
  def initialize(string)
    super
  end

  def shouting?
    all_uppercase?
  end

  def questioning?
    end_with?("?")
  end

  def silent?
    strip.length.zero?
  end

  private

  def all_uppercase?
    has_letters? && self == upcase
  end

  def has_letters?
    self =~ /[[:alpha:]]/
  end
end

# A single stimulus->response association. Can test an incoming
# message to see if it matches its stimulus criteria
class Response
  attr_reader :stimulus, :response

  def initialize(stimulus, response)
    @stimulus, @response = stimulus, response
  end

  def test?(message)
    default? || message.send(stimulus)
  end

  def default?
    stimulus == :default
  end
end

# Monkeypatch Array so that delete_at takes an optional block
class Array
  alias :delete_at_without_block :delete_at

  def delete_at(element=nil, &block)
    element = index(&block) if block_given?
    delete_at_without_block(element)
  end
end

# Manages a collection of Responses, and knows how to find the
# appropriate response for a message by testing each Response against
# the message, and returning the matching Response's response. So THIS
# is responsive design, right?
class Behavior
  def initialize(options)
    @responses = make_responses(options)
  end

  def determine_response_to(message)
    find_response_for(message).response
  end

  private

  def make_responses(options)
    responses = options.map {|stimulus, response| Response.new(stimulus, response) }
    move_default_to_end(responses)
  end

  def move_default_to_end(responses)
    responses << responses.delete_at {|response| response.default? }
  end

  def find_response_for(message)
    @responses.detect {|response| response.test?(message) }
  end
end

# Your basic semicommunicative teenager
class Bob
  def initialize
    @behavior = Behavior.new(:default => "Whatever.",
                             :shouting? => "Woah, chill out!",
                             :questioning? => "Sure.",
                             :silent? => "Fine. Be that way!")
  end

  def hey(message)
    @behavior.determine_response_to ConversationalString.new(message)
  end
end
