# Bob is a lackadaisical teenager. In conversation, his responses are very limited.
# Bob answers 'Sure.' if you ask him a question.
# He answers 'Whatever.' if you tell him something.
# He answers 'Woah, chill out!' if you yell at him (ALL CAPS).
# He says 'Fine. Be that way!' if you address him without actually saying anything.
class Bob
    # Say something to Bob
    def hey(message)
        case message
        when nil, /^\s*$/  # empty message
            return "Fine. Be that way!"
        when /^[^a-z]+$/  # ALL-CAPS
            return "Woah, chill out!"
        when /\?$/  # question
            return "Sure."
        else
            return "Whatever."
        end
    end
end
