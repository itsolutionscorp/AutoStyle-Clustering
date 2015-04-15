#!/usr/bin/env ruby

class Bob
  def hey(m)
    # If m is nil, or the string is empty after removing spaces...
    return 'Fine. Be that way!' if m.nil? or m.rstrip.empty?

    # If m is all caps...
    return 'Woah, chill out!' if m.upcase == m

    # If m ends with a ?
    return 'Sure.' if m =~ /\?$/

    # Otherwise...
    'Whatever.'
  end
end
