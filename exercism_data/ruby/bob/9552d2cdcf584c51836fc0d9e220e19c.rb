class Bob

    def hey (input)
        @input = input

        if is_empty?
            "Fine. Be that way!"

        elsif is_yelling?
            "Woah, chill out!"

        elsif is_asking_a_question?
            "Sure."

        else
            "Whatever."
        end

    end

    def is_yelling?
        if @input == @input.upcase
            true
        else
            false
        end
    end

    def is_asking_a_question?
        if @input[-1] == "?"
            true
        else
            false
        end
    end

    def is_empty?
        if @input.strip.empty?
            true
        else
            false
        end
    end
end
