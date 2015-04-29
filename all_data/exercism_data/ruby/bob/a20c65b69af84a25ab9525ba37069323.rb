#!/usr/bin/env ruby
# encoding: utf-8

# Program to simulate a conversation.
# Bob answers 'Sure.' if you ask him a question.
# He answers 'Woah, chill out!' if you yell at him.
# He says 'Fine. Be that way!' if you address him without actually sayin$
# He answers 'Whatever.' to anything else.
class Bob
  def hey(phrase)
    # If the "phrase" is empty
    if phrase.strip.empty?
      return 'Fine. Be that way!'
    # If the "phrase" contains letters and they are in uppercase
    elsif  phrase =~ /[A-Z]/ && phrase.upcase == phrase
      return 'Woah, chill out!'
    # If the phrase ends with "?"
    elsif phrase.end_with?('?')
      return 'Sure.'
    else
    # Otherwise
      return 'Whatever.'
    end
  end
end
