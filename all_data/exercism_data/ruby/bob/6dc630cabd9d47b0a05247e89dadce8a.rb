# This module is home to the Bob class.
#
# Author::  Tim Henigan
# Copyright:: Copyright (c) 2013
# License::   MIT (http://opensource.org/licenses/MIT)

# Bob is a lackadaisical teenager. In conversation, his responses are very
# limited.

class Bob

  def hey(request)
    r = Request.new(request)

    if r.is_empty?
      "Fine. Be that way!"
    elsif r.is_yelled?
      "Woah, chill out!"
    elsif r.is_question?
      "Sure."
    else
      "Whatever."
    end
  end

end

# Determine characteristics of a given request

class Request

  def initialize(request = "")
      @request = request
  end

  def is_empty?
    @request.nil? or @request.rstrip.empty?
  end

  def is_yelled?
    @request == @request.upcase
  end

  def is_question?
    @request.rstrip.end_with?("?")
  end

end
