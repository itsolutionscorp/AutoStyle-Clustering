# Wanted to have a bit of fun here, yes I know it's overkill!

# During communication, the process _might_ be something like this:
# 1) Our brains detects a signal from our sensory organ, in this case our ears
# 2) Our brain begins to process the activity, receiving input from our sensory organ
# 3) Our left hemisphere processes the input and makes a decision as to the appropriate response
# 4) If a response is warrented, a response organ is given an input and told to output in whatever means it wishes

class Ears
  attr_reader :message

  def initialize(message)
    @message = message.to_s
  end

  def mute?
    message.empty?
  end

  def asking?
    message.end_with?("?")
  end

  def shouting?
    message == message.upcase
  end
end

class Mouth
  def self.output(words)
    speak(words)
  end

  def self.speak(words)
    words
  end
end

class Thought
  attr_accessor :thought

  def initialize(thought)
    @thought = thought
  end
  
  def warrants_a_response?
    @thought.is_a?(Symbol)
  end

  def as_neurons
    @thought
  end
end

class LeftHemisphere
  
  RESPONSES = {
    :chill    => "Woah, chill out!",
    :whatever => "Whatever.",
    :sure     => "Sure.",
    :fine     => "Fine. Be that way!"
  }

  FALLBACK = :whatever

  def self.process(input, output_organ = Mouth)
    if response_to(input).warrants_a_response?
      output_organ.output(RESPONSES.fetch(response_to(input).as_neurons) { RESPONSES[FALLBACK] })
    else
      DeadResponse.new
    end
  end

  def self.response_to(input)
    response = if input.mute?
      :fine
    elsif input.shouting?
      :chill
    elsif input.asking?
      :sure
    else
      :whatever
    end
    Thought.new(response)
  end
end

class DeadResponse
  def inspect
    "..."
  end
end

class BrainActivity
  attr_accessor :response

  def initialize(activity, sense = :sound)
    @response = LeftHemisphere.process(sensory_organ_from(sense).new(activity))
  end
  
  private

  def sensory_organ_from(sense)
    if sense == :sound
      Ears
    end
  end

end


class Bob
  def hey(said)
    brain_receives_sound(said)
  end

  private

  def brain_receives_sound(activity)
    BrainActivity.new(activity, :sound).response
  end
end
