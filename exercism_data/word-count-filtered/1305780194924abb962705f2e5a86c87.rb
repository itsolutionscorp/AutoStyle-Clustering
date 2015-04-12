require 'pry-debugger'

class Phrase
	def initialize(phrase)
		@phrase = phrase
	end

	def word_count
		{@phrase => 1}

    tokens = @phrase.gsub(/[^A-Za-z0-9\w]+/, ' ').split.map(&:downcase)

    tokens.inject({}) do | count, token |
      #binding.pry

      if count.has_key? token
        count[token] = count[token] + 1
      else
        count[token] = 1
      end
      count
    end

	end
end
