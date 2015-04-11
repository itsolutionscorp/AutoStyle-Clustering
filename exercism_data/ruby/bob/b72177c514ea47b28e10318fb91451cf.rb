module Reactions
  class << self
    def list
      @list ||= []
    end

    def detect(phrase)
      list.detect { |r| r.match(phrase) }
    end
  end

  class Reaction
    DEFAULT_RESPONSE = 'Whatever.'.freeze

    class << self
    private
      def inherited(klass)
        super
        Reactions.list << klass.new
      end
    end

    def match(phrase)
      raise NotImplementedError
    end

    def react
      self.class::RESPONSE
    end
  end
end

module Reactions
  class Shoute < Reaction
    RESPONSE = 'Woah, chill out!'.freeze

    def match(phrase)
      !phrase.empty? and phrase.upcase == phrase
    end
  end
end

module Reactions
  class Question < Reaction
    RESPONSE = 'Sure.'.freeze

    def match(phrase)
      phrase.end_with?('?')
    end
  end
end

module Reactions
  class Silent < Reaction
    RESPONSE = 'Fine. Be that way!'.freeze

    def match(phrase)
      phrase.empty?
    end
  end
end

class Bob
  def hey(phrase)
    reaction = Reactions.detect(phrase.strip)
    reaction ? reaction.react : Reactions::Reaction::DEFAULT_RESPONSE
  end
end
