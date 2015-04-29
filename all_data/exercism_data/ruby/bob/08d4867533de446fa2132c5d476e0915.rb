# ======================================================================
# NITPICKERS README
# ======================================================================
#
# This section contains what I was thinking and where I know I could
# use more help. Obviously if you see somewhere else an improvement
# can be made, that's what nitpicking is all about, so I'm all ears!
#
# ----------------------------------------------------------------------
# What Was I Thinking?!?
# ----------------------------------------------------------------------
#
# Since Exercism is about practice, and only *perfect* practice makes
# perfect--because practice only makes *permanent*--I have tried my
# level best to make this the most perfect code I could.
#
# I wrote this code as an exercise in rigorously following hardcore OO
# rules. These are the explicit rules I tried to follow:
#
# * All tests must pass.
# * No class may exceed 25 lines of code.
# * No method may exceed four lines of code.
# * No method may receive more than four arguments.
# * Reek must not report any warnings.
# * Roodi must not report any warnings.
#
# I also had a rule that said "every class must be in its own file"
# until I discovered that the exercism tool would only let me submit
# one file. Heh.
#
# ----------------------------------------------------------------------
# Areas Where I Know I Could Do Better
# ----------------------------------------------------------------------
#
# * ChattyPerson#initialize has a hardcoded reference to the Behavior
#   class. This creates a fixed dependency between the classes, but I
#   do not see a way to inject the dependency.
#
# * Array#delete_at is monkeypatched directly onto the Array class. I
#   tried doing it with a module, but for some reason this fails to
#   overload the #delete_at method; possibly because the method
#   signature changes, I dunno.

# Decorates String with conversation-relevant query methods
class ConversationalString < String
  def shouting?
    has_letters? && self == upcase
  end

  def questioning?
    ends_with? "?"
  end

  def has_letters?
    self =~ /[[:alpha:]]/
  end

  def silent?
    strip.length.zero?
  end

  def ends_with?(string)
    self[-string.length..-1] == string
  end
end

# A single stimulus->response association. Can test an incoming
# message to see if it matches its stimulus criteria
class Response
  attr_reader :stimulus, :response

  def initialize(stimulus, response)
    @stimulus, @response = stimulus, response
  end

  def test(message)
    message.send stimulus
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
    @responses = options.map {|stimulus, response| Response.new(stimulus, response) }
    @default = @responses.delete_at {|response| response.stimulus == :default }
  end

  def determine_response_to(message)
    (@responses.detect {|response| response.test message } || @default).response
  end
end

# A person who can receive 'hey' messages and respond with the
# appropriate message
class ChattyPerson
  attr_reader :behavior

  def initialize(behavior_list)
    @behavior = Behavior.new(behavior_list)
  end

  def hey(message)
    correct_answer_for(ConversationalString.new(message))
  end

  private

  def correct_answer_for(message)
    behavior.determine_response_to message
  end
end

# Your basic semicommunicative teenager
class Bob < ChattyPerson
  def initialize
    super :default => "Whatever.",
          :shouting? => "Woah, chill out!",
          :questioning? => "Sure.",
          :silent? => "Fine. Be that way!"
  end
end
