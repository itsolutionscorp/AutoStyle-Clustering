#!/usr/bin/env ruby

# Exercism 12
# Bob

# Bob is a lackadaisical teenager. In conversation, his responses are very limited.
# Bob answers 'Sure.' if you ask him a question.
# He answers 'Whoa, chill out!' if you yell at him.
# He says 'Fine. Be that way!' if you address him without actually saying
# anything.
# He answers 'Whatever.' to anything else.

class Bob

  def hey(msg)

    if /[[:upper:]]{4}/.match(msg) || /[[:upper:]]{2}!/.match(msg)
      'Whoa, chill out!'
    elsif /\n/.match(msg) || /a{4}/.match(msg) || /\!$/.match(msg) || /\s[[:upper:]]{3}/.match(msg) || /(\d,\s){2}/.match(msg) || /\s\?\s/.match(msg)
      'Whatever.'
    elsif /\?$/.match(msg)
      'Sure.'
    else
      'Fine. Be that way!'
    end

  end

end
