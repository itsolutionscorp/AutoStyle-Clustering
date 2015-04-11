class Bob
  def hey(string)
    case string.strip
      when '';              silence
      when string.upcase;   shout
      when /\?\z/;          question
      else;                 'Whatever.'
    end
  end

  def shout
    'Woah, chill out!'
  end

  def question
    'Sure.'
  end

  def silence
    'Fine. Be that way!'
  end
end
