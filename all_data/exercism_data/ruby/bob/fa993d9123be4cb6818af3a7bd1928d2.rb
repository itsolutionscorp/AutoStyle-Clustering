Responses = { 
  :is_shouting? => 'Woah, chill out!',
  :is_question? => 'Sure.',
  :is_silence? => 'Fine. Be that way!'
}

class Bob 
  def hey(message)    
    statement = Statement.new(message)    
    Responses.find(lambda {[:default, 'Whatever.']}) { |k, v| statement.send(k) }[1]
  end
end

class Statement < String
  def initialize(string)
    super(string)
  end
  
  def is_shouting?
    word_chars = chars.select { |c| c =~ /[[:alpha:]]/ }
    word_chars.length > 0 && word_chars.all? { |c| c =~ /[A-Z]/ }
  end
  
  def is_question?
    end_with? '?'
  end
  
  def is_silence?
    strip.empty?
  end  
  
end
