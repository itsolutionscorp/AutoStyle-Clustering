class Bob
  def hey(phrase)
    case
    when shouting?(phrase)
      'Woah, chill out!'
    when question?(phrase)
      'Sure.'
    when silence?(phrase)
      'Fine. Be that way!'
    else
        'Whatever.'
      # if (phrase) == 'WATCH OUT!'
#       'Woah, chill out!'
#     elsif phrase == 'Does this cryogenic chamber make me look fat?'
#       'Sure.'
#     else
#       'Whatever.'
    end
end

def question?(s)
  s.end_with?('?')
end

def shouting?(s)
  s =~ /[A-Z]/ && s.upcase == s
end

def silence?(s)
  s.strip.empty?
end

end
