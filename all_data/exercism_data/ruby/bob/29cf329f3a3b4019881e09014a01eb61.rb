class Teenager
  def hey words
    words = Words.new(words)
    
    words.respond
  end
end

class Bob < Teenager
end

class Words
  def initialize(words)
    @words = words
  end

  def respond
    case @words.to_s
    when ''
      'Fine. Be that way!'
    when @words.upcase
      'Woah, chill out!'
    when /\?$/
      'Sure.'
    else
      'Whatever.'
    end
  end
end
