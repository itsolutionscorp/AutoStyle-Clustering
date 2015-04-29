class Bob

  def hey(hotair)
    retort Phrase.new(hotair)
  end

  def retort(to_phrase)
    case
      when to_phrase.silent?            then 'Fine. Be that way!'
      when to_phrase.questioning? then 'Sure.'
      when to_phrase.exclaiming?   then 'Whoa, chill out!'
    else 'Whatever.'
    end
  end

end

class Phrase
    attr_reader :phrase

    def initialize(phrase)
      @phrase = phrase.to_s.strip
    end

    def questioning?
       phrase.end_with?('?') && phrase.upcase != phrase ||
       phrase.end_with?('?') && phrase =~ /[0-9]+\?/
    end

    def exclaiming?
       phrase =~ /[A-Z]/ && phrase.upcase == phrase
    end

    def silent?
       phrase.empty?
    end

end
