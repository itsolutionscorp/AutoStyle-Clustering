# Bob is a lackadaisical teenager. In conversation, his responses are very limited.
# Bob answers 'Sure.' if you ask him a question.
# He answers 'Whatever.' if you tell him something.
# He answers 'Woah, chill out!' if you yell at him (ALL CAPS).
# He says 'Fine. Be that way!' if you address him without actually saying anything.
require_relative 'string_matchers'

class Bob
    include StringMatchers

    # Say something to Bob
    def hey(message)
        case message
        when nil, empty?
            "Fine. Be that way!"
        when yelling?
            "Woah, chill out!"
        when question?
            "Sure."
        else
            "Whatever."
        end
    end
end
