class InputType
    def initialize
        @regex = /.*/
        @checks = [:matches_regex]
    end

    def matches_regex(input)
        !!input.match(@regex)
    end

    def passes_all_checks(input)
        for check in @checks.map { |symbol| method(symbol) }
            return false unless check.call input
        end
        true
    end
end

class Shout < InputType

    def not_all_acronyms(input)
        for word in input.split(/[,\.\?! ]/)
            return true unless @acronyms.include? word
        end
        false
    end

    def all_uppercase(input)
        input == input.upcase
    end

    def initialize
        @checks = [:matches_regex, :all_uppercase, :not_all_acronyms]
        @acronyms = ["OK", "DMV"]
        @regex = /\b[A-Z]+\b/
    end
end

class Question < InputType
    def initialize
        super
        @regex = /\?\Z/
    end
end

class Silence < InputType 
    def initialize
        super
        @regex = /\A\s*\Z/
    end
end

class Statement < InputType
end

class Responder
    def initialize
        @responses = {}
    end

    def hey(input)
        respond_to input_type input
    end

    private

    def respond_to(type)
        @responses[type]
    end

    def input_type(input)
        for type_name in @responses.keys
            type = Object.const_get(type_name).new
            return type_name if type.passes_all_checks input
        end
        return "InputType"
    end
end

class Bob < Responder
    def initialize
        @responses = {

            "Shout" => "Woah, chill out!",
            "Question" => "Sure.",
            "Silence" => "Fine. Be that way!",
            "Statement" => "Whatever."
        }
    end
end
