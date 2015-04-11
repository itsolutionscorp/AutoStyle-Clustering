class Bob
  LOGIC_METHODS = [:blank, :caps, :question, :all]

  def hey(sentence)
    sentence.to_s.strip!
    LOGIC_METHODS.each do |m|
      if response = send(m, sentence)
        return response
      end
    end
  end

  private
    def blank(sentence)
      "Fine. Be that way!" if "" == sentence
    end

    def caps(sentence)
      "Woah, chill out!" if sentence.upcase == sentence
    end

    def question(sentence)
      "Sure." if sentence.end_with?("?")
    end

    def all(sentence)
      "Whatever."
    end
end
