class Bob
  RESPONSE_OPTIONS = [:nothing, :yell, :question, :anything_else]

  def hey(sentence)
    sentence.to_s.strip!
    choose_response(sentence)
  end

  private
    def choose_response(sentence)
      send(RESPONSE_OPTIONS.find { |m| send(m, sentence) }, sentence)
    end

    def nothing(sentence)
      "Fine. Be that way!" if sentence.empty?
    end

    def yell(sentence)
      "Woah, chill out!" if sentence.upcase == sentence
    end

    def question(sentence)
      "Sure." if sentence.end_with?("?")
    end

    def anything_else(sentence)
      "Whatever."
    end
end
