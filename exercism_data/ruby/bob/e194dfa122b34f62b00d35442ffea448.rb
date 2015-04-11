class Bob

    def initialize()

    end

    def hey(input)

        input = input.strip
        response = 'Whatever.'

        if input.upcase == input && input =~ /[A-Z]/
            return 'Woah, chill out!'
        end

        last_char = getLastChar(input)

        if last_char == '?'
            return 'Sure.'
        end

        if input.empty?
            response = 'Fine. Be that way!'
        end

        response
    end

    def getLastChar(text)
        last_char = text[-1,1]
    end

    def stringString(text)
        input = text.gsub(/[^0-9A-Za-z]/, '')
        puts '--- #{input} ---'
    end

    def numeric?(text)
        text.match(/^[0-9]+$/)
    end
end
