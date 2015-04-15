class Bob
  def hey message
    responses.find do |message_type, response|
      message_type =~ message.to_s
    end.last
  end

  private
    def responses
      @responses ||= {
        silence   => "Fine. Be that way.",
        all_caps  => "Woah, chill out!",
        question  => "Sure.",
        something => "Whatever."
      }
    end

    def silence
      @silence ||= /\A\z/
    end

    def all_caps
      @all_caps ||= /\A[^a-z]+\z/
    end

    def question
      @question ||= /\?\z/
    end

    def something
      @someting ||= /.+/
    end
end
