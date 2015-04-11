class Bob
    def hey(what_they_said)
        blahblah = normalize(what_they_said)
        choose_proper_response_to(blahblah)[:response]
    end

    private
    def choose_proper_response_to(words)
        choices = [
            {:if => :silence?, :response => 'Fine. Be that way!' },
            {:if => :yelling?, :response => 'Woah, chill out!' },
            {:if => :question?, :response => 'Sure.' },
            {:if => :otherwise, :response => 'Whatever.' }
        ]
        choices.find { |choice| send(choice[:if], words) }
    end

    def normalize(input)
        (input || '').delete(' ')
    end

    def silence?(words)
        words.empty?
    end

    def yelling?(words)
        words.upcase == words
    end

    def question?(words)
        words.end_with?('?')
    end

    def otherwise(words)
        true
    end
end
