module Sense
  Sensations = []

  def sense(input)
    sensed = Sensations.find do |m|
      public_send(m, input)
    end
    sensed.to_s.chomp!("?").to_sym unless sensed.nil?
  end

  module Voice
    include Sense

    def shout?(heard)
      heard == heard.upcase && heard != heard.downcase
    end

    def query?(heard)
      heard.strip.end_with?("?")
    end

    def quiet?(heard)
      heard.strip.empty?
    end

    def Voice.included(other)
      other::Sensations.push *instance_methods(false)
    end
  end
end

module React
  Reactions = {}

  def react(sensed)
    Reactions[sensed]
  end

  module Surly
    include React

    Surlies = {
      shout: "Woah, chill out!",
      query: "Sure.",
      quiet: "Fine. Be that way!",
    }
    Surlies.default = "Whatever."

    def Surly.included(other)
      other::Reactions.merge! Surlies
      other::Reactions.default = Surlies.default
    end
  end
end

class Bob
  include Sense::Voice
  include React::Surly

  def hey(heard)
    react sense(heard)
  end
end
