class Bob
  def initialize
  end
  
  def hey(message)
    response = case gen_answare message
      when :yell then 'Woah, chill out!'
      when :'?'    then "Sure."
      when :n then 'Fine. Be that way!'
      else 'Whatever.'
    end
    return response
  end
  
  def gen_answare(mess)
    return :yell if yell? mess
    return :'?'    if quastion? mess
    return :n if n? mess
    return whatever
  end
  def quastion?(mess)
    #mess['?']
    mess.end_with? '?'
  end
  
  def yell?(mess)
    mess.upcase == mess && mess.downcase != mess
  end
  
  def n?(mess)
    mess.strip == ''#=~ /^\s*$/
  end
  
  def whatever
    true
  end
end
