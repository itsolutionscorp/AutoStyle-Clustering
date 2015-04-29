class Bob
    def hey(text)
        request = Request.new(text)

        case
        when request.is_shouting
            return 'Woah, chill out!'
        when request.is_question
            return 'Sure.'
        when request.is_empty
            return 'Fine. Be that way!'
        else
            return 'Whatever.'
        end
    end
end

class Request
    attr_accessor :string

    def initialize(a_string)
        @string = a_string.to_s
    end

    def is_shouting
        # Contains alphabet characters and
        # they're all uppercase; shouting.
        return @string.match(/[[:alpha:]]/)
             && @string.upcase == @string
    end

    def is_question
        return @string.end_with?('?')
    end

    def is_empty
        # The 'strip' here just removes whitespace
        # as we are treating that as not saying anything.
        return @string.strip.empty?
    end

end
