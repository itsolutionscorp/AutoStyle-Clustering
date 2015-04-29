#!/bin/ruby

# Bob.rb
# @author Count Jocular
#
# Bob is a lackadaisical teenager.
#

class Bob
  def hey( the_input )
    the_type = the_input.class
    raise "Input is #{the_type} (expected String)." if the_type != String
    return "Fine. Be that way!" if hears_silence( the_input )
    return "Woah, chill out!" if hears_shouting( the_input )
    return "Sure." if hears_question( the_input )
    "Whatever."
  end

  #------#
  private
  #------#
  def hears_silence( the_input )
    the_input.gsub(' ', '') == ""
  end

  def hears_shouting( the_input )
    the_input.upcase == the_input
  end

  def hears_question( the_input )
    the_input.end_with?('?')
  end
end
