class Bob
  def hey(phrase)
    case
    when getting_the_silent_treatment?(phrase)
      "Fine. Be that way."
    when being_yelled_at?(phrase)
      "Woah, chill out!"
    when being_questioned?(phrase)
      "Sure."
    else
      "Whatever."
   end
  end

  private
    def reply_with
      yield if block_given?
    end

    def getting_the_silent_treatment?(phrase)
      phrase.nil? || phrase.empty?
    end

    def being_yelled_at?(phrase)
      phrase == phrase.upcase
    end

    def being_questioned?(phrase)
      phrase.end_with? "?"
    end
end
