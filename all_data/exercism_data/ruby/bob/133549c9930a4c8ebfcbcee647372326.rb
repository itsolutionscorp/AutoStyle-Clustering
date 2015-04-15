class Bob
  attr_accessor :words

  def hey(words)
    self.words = words
    response
  end

  private
    def response
      case
      when shouting
        'Whoa, chill out!'
      when questioning
        'Sure.'
      when silent
        'Fine. Be that way!'
      else
        'Whatever.'
      end
    end

    def shouting
      words == words.upcase && words =~ /[A-Z]/
    end

    def questioning
      words[-1] == '?'
    end

    def silent
      words.strip == ''
    end
end
