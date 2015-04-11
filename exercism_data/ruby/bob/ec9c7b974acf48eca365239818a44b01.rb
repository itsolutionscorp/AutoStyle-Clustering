#! /usr/bin/env ruby
# -*- coding: UTF-8 -*-

class Bob
  def hey msg
    return 'Fine. Be that way.' if msg.nil? or msg.length == 0
    return 'Woah, chill out!' unless msg =~ /[a-z]/
    return 'Sure.' if msg.end_with? '?'
    'Whatever.'
  end
end
