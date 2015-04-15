class Bob
    def initialize
        @call_and_response = [
            [:silence?, 'Fine. Be that way!'],
            [:yelling?, 'Woah, chill out!'],
            [:question?, 'Sure.'],
            [:otherwise, 'Whatever.']
        ]
    end

    def hey(input)
        words = trim(input)
        response = @call_and_response.find { |cr|
            send(cr.first, words)
        }
        response.last
    end

    private
    def trim(input)
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
