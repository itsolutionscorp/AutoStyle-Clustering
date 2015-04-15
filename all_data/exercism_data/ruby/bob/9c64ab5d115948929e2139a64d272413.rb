#!/usr/local/lib/ruby/1.9.1/yaml.rb
# -*- coding: UTF-8 -*-
# Documentation comment
class Bob
  def hey(chat)
    if chat.lstrip == ''
      return 'Fine. Be that way!'
    elsif chat.upcase == chat
      return 'Woah, chill out!'
    elsif chat[-1] == '?'
      return 'Sure.'
    else
      return 'Whatever.'
    end
  end
end
