class Bob

  def hey(sentence)
    @sentence = sentence

    choose_answer
  end

  private

    def choose_answer
      answer = 'Whatever.'
      case
      when blank? then answer = 'Fine. Be that way!'
      when is_uppercase? then answer = 'Woah, chill out!'
      when is_question? then answer = 'Sure.'
      end

      answer
    end

    def is_uppercase?
      @sentence == @sentence.upcase
    end

    def is_question?
      @sentence.split('').pop == '?'
    end

    def blank?
      @sentence.nil? || @sentence.strip.empty?
    end
end
