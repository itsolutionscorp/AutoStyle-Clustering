# This module is home to the Bob class.
#
# Author::  Tim Henigan
# Copyright:: Copyright (c) 2013
# License::   MIT (http://opensource.org/licenses/MIT)

# Bob is a lackadaisical teenager. In conversation, his responses are very
# limited.

class Bob

  # * Answer 'Sure.' when asked a question.
  # * Answer 'Woah, chill out!' if you yell (ALL CAPS).
  # * Answer 'Fine. Be that way!' if you address him without actually saying
  # anything.
  # * Answer 'Whatever.' to anything else.
  def hey(request)

    if not request or request.strip.length == 0
      response = "Fine. Be that way!"
    elsif request == request.upcase()
      response = "Woah, chill out!"
    elsif request.rstrip().end_with?("?")
      response = "Sure."
    else
      response = "Whatever."
    end

  end
end
