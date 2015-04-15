class Bob

  def hey(text)
    statement = :silence if detect_silence(text)
    statement ||= :exclamatory if detect_exclamation(text)
    statement ||= :interrogative if detect_question(text)
    statement ||= :declarative
    response = responses[statement]
  end

  private
    def responses
      { :declarative =>   'Whatever.',
        :exclamatory =>   'Woah, chill out!',
        :interrogative => 'Sure.',
        :silence => 'Fine. Be that way!'
      }
    end

    def detect_silence(text)
      text == nil || text.gsub(/ /, '') == ''
    end

    def detect_exclamation(text)
      text ||= '' #Prevent error from attempting to upcase nil
      text == text.upcase
    end

    def detect_question(text)
      /\?$/.match(text)
    end
end
