class Bob
  def hey(phrase)
    return reply("Fine. Be that way.") if getting_the_silent_treatment?(phrase)
    return reply("Woah, chill out!") if being_yelled_at?(phrase)
    return reply("Sure.") if being_questioned?(phrase)
    reply
  end

  private
    def reply(response = "Whatever.")
      response
    end

    def getting_the_silent_treatment?(phrase)
      phrase.nil? || phrase.empty?
    end

    def being_yelled_at?(phrase)
      phrase == phrase.upcase
    end

    def being_questioned?(phrase)
      phrase[-1] == "?"
    end
end
