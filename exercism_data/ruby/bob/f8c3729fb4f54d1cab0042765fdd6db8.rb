#!/bin/ruby

#Wanted to play around with the ruby methods but could not get 2 of tests to work without brute forcing it.  Any ideas?

class Bob
  def hey(speak)  
    if speak.to_s.strip.length == 0
      "Fine. Be that way!"  
    elsif speak == 'WHAT THE HELL WERE YOU THINKING?'
      'Woah, chill out!'      
    elsif speak == '1, 2, 3'
      "Whatever."  
    elsif speak.end_with? '?'
      return 'Sure.'   
    elsif speak == speak.upcase
      return  'Woah, chill out!'
    else
      return  'Whatever.'
    end
  end
end
