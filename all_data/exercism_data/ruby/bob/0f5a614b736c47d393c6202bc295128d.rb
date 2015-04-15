class Bob

  module Respondable
    def answer_to(phrase)
      @response if match?(phrase.to_s)
    end
  end

  module YouSaidNothing
    extend Respondable
    @response = 'Fine. Be that way!'

    def self.match?(phrase)
      phrase.strip == ''
    end
  end

  module DontYellAtMe
    extend Respondable
    @response = 'Woah, chill out!'

    def self.match?(phrase)
      phrase == phrase.upcase
    end
  end

  module IDontTakeQuestionsSeriously
    extend Respondable
    @response = 'Sure.'

    def self.match?(phrase)
      phrase.end_with?('?')
    end
  end

  module Whatevs
    extend Respondable
    @response = 'Whatever.'

    def self.match?(phrase)
      true
    end
  end

  def hey(what_a_parent_said)
    # Try each of our respondable modules in this exact order:
    ways_to_respond = [
      YouSaidNothing,
      DontYellAtMe,
      IDontTakeQuestionsSeriously,
      Whatevs
    ]
    # The first one that returns a non-nil value we'll assume
    # was the appropriate match for the given incoming phrase.
    ways_to_respond.each do |respondable|
      if response = respondable.answer_to(what_a_parent_said)
        # We found a match! Return this to the caller of `hey`
        return response
      end
    end
  end
end
