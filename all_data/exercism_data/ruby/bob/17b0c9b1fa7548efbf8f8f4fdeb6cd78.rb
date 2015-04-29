class Bob

  def hey(phrase)
    answers[phrase_type(phrase)]
  end

  private
    def answers
      answers = Hash[ nothing:  "Fine. Be that way!",
                        shout:  "Woah, chill out!",
                     question:  "Sure." ]

      answers.default = "Whatever."
      answers
    end

    def phrase_type(phrase)
      answers.keys.find { |answer| eval "#{answer}?(phrase)" }
    end

    def nothing?(phrase)
      phrase.strip.empty?
    end

    def shout?(phrase)
      words(phrase) && loud_words(phrase)
    end

    def question?(phrase)
      phrase.end_with?("?")
    end

    def words(phrase)
      phrase[/[[:alpha:]]+/]
    end

    def loud_words(phrase)
      words(phrase) == phrase[/[[:upper:]]+/]
    end
end
