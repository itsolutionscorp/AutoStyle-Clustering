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
    return "Fine. Be that way!" if the_input.gsub(' ', '') == ""
    return "Woah, chill out!" if the_input.upcase == the_input
    return "Sure." if the_input[-1,1] == '?'
    "Whatever."
  end
end
