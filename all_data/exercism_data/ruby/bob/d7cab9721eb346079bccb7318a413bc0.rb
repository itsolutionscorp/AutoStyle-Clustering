class Bob
    def hey(request)
        # Pull out only alphabet characters.
        alpha = request.to_s.gsub(/[^A-Za-z]/, '')

        case
        when alpha.length > 0 && alpha.upcase! == nil
            # Contains alphabet characters and
            # they're all uppercase; shouting.
            return 'Woah, chill out!'
        when request.to_s.end_with?('?')
            # Ends with ?; a question.
            return 'Sure.'
        when request.to_s.strip.length == 0
            # Nothing said...
            return 'Fine. Be that way!'
        else
            return 'Whatever.'
        end
    end
end
