class Bob
  def initialize
    
  end

  def hey(string)
    if string.nil? || string.empty?
      'Fine. Be that way!'
    elsif string !~ /[[:lower]]/
      'Woah, chill out!'
    elsif string =~ /\?$/
      'Sure.'
    else
      'Whatever.'
    end

  end
  
  
end
