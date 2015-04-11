#!/usr/bin/env ruby

class Bob
  def hey(m)
    return 'Fine. Be that way!' if m.nil? or m.rstrip.empty?
    return 'Woah, chill out!' if m.upcase == m
    return 'Sure.' if m =~ /\?$/
    'Whatever.'
  end
end
