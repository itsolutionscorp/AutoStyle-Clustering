class Bob
  def hey text
    phrase = Phrase.new text

    case 
      when phrase.silence? then 'Fine. Be that way!' 
      when phrase.shouting? then 'Whoa, chill out!'   
      when phrase.question? then 'Sure.'              
      else 'Whatever.'
    end
  end

  class Phrase
    def initialize text
      @text = text
    end

    def silence?
      @text =~ /\A\s*\Z$/
    end

    def question?
      @text.end_with? '?'
    end

    def shouting?
      all_letters_uppercase?
    end

    def all_letters_uppercase?
      letters = @text.scan(/[a-z]/i)
      letters.any? && letters.all? {|l| l.upcase == l }
    end
  end
end
