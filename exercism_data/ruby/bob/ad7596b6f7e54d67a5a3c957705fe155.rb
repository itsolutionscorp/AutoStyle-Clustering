#!/usr/bin/env ruby 

class Bob

  # I know this can be done as a simple if/else but I
  # thought I'd see if I could do it with a lookup table. :)
  RESPONSES = {
    lambda {|s| s.strip.empty?} => "Fine. Be that way!",
    lambda {|s| !s.strip.empty? && s == s.upcase} => "Woah, chill out!",
    lambda {|s| s.end_with?("?")} => "Sure.",
    lambda {|s| true} => "Whatever."
  }

  def hey(str)

    RESPONSES.each do |l, s|
      return s if l.call(str)
    end

    raise "Response not found."
  end
end
