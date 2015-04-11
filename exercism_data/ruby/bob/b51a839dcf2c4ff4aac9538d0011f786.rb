# This module is home to the Bob class.
#
# Author::  Tim Henigan
# Copyright:: Copyright (c) 2013
# License::   MIT (http://opensource.org/licenses/MIT)

# Bob is a lackadaisical teenager. In conversation, his responses are very
# limited.

class Bob

  def hey(request)
    if request_was_empty?(request)
      "Fine. Be that way!"
    elsif request_was_yelled?(request)
      "Woah, chill out!"
    elsif request_was_question?(request)
      "Sure."
    else
      "Whatever."
    end
  end

  def request_was_empty?(r)
    r.nil? or r.empty?
  end

  def request_was_yelled?(r)
    r == r.upcase
  end

  def request_was_question?(r)
    r.rstrip.end_with?("?")
  end

end
