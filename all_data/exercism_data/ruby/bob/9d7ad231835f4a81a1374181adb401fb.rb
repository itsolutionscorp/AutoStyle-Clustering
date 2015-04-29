class Bob
  attr_accessor :response

  LOGIC_METHODS = [:blank, :caps, :question, :all]

  def hey(sentence)
    @response = nil
    sentence.strip!
    LOGIC_METHODS.each do |m|
      send(m, sentence) unless @response
    end
    @response
  end

  private
    def blank(sentence)
      @response = "Fine. Be that way!" if "" == sentence
    end

    def caps(sentence)
      @response = "Woah, chill out!" if sentence.upcase == sentence
    end

    def question(sentence)
      @response = "Sure." if sentence.end_with?("?")
    end

    def all(sentence)
      @response = "Whatever."
    end
end
