#!/usr/bin/env ruby

class Bob
  def hey s
    return 'Woah, chill out!' if s.upcase == s and s.upcase != s.downcase
    return 'Sure.' if s.end_with? '?'
    return 'Fine. Be that way!' if s.strip == ''
    'Whatever.'
  end
end
