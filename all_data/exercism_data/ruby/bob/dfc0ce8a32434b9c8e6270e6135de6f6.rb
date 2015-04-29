class Bob

  #lesson learned - regexes are tricky. 
  #the remaining couple were not a good use of time.

  def hey(input)
    #numbers and num questions     
    if input.match(/(\d, )+\d$/) #'1, 2, 3'
      "Whatever."
    elsif input.match(/(\d)[?]/) #4?
      "Sure."  
    #---------------------- 
    elsif input.match(/[A-Z\s]+!$/) #shouting
      'Whoa, chill out!'
    elsif input.match(/[\w\s]+[!][\w\s]+[.][\w\s]+[?]/) #prattling
      'Sure.'
    elsif input.match(/[A-Z\s]+\?$/) #foreceful question
      'Whoa, chill out!'   
    elsif input.match(/[A-Za-z]+\?$/) #question
      'Sure.'
    elsif input.match(/\.\z/) #statement
      'Whatever.'
    elsif input.match(/[!$]/) #talking forcefully ends with !
      "Whatever."
    elsif input.match(/^[\s]+$/) #"   "
      "Fine. Be that way!"
    elsif input.match(/\A\Z/) #empty string
      'Fine. Be that way!'
    elsif input.match(/[A-Z\s]+[^!]/) #shouting no !
      'Whoa, chill out!'  
    elsif input.match(/[a-zA-Z0-9\s\?$]/) #questoin with #
      'Sure.'
    elsif input.match(/[\\t]+/) #weird silence
      'Fine. Be that way!'
    elsif input.match(/[?][^?]/) #question in middle
      'Whatever.'
    elsif input.match(/[A-Z]+$/) #shouting gibberish
      'Whoa, chill out!'    
    else
      'Whatever.'
    end
  end
end
