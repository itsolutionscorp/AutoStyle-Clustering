class Bob
  class Shouting
    def self.===(input)
      input == input.upcase
    end
  end

  class Empty
    def self.===(input)
      input.strip.empty?
    end
  end

  class Question
    def self.===(input)
      input.end_with? '?'
    end
  end


  def hey(phrase)
    phrase ||= ''

    case phrase
    when Empty     then 'Fine. Be that way!'
    when Shouting  then 'Woah, chill out!'
    when Question  then 'Sure.'
    else
      'Whatever.'
    end
  end

end
