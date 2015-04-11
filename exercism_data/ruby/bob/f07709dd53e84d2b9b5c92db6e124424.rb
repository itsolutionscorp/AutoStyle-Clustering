class Bob
    def hey(text)
        begin
            answer = teenager_answer(text)
        rescue
            answer = responds[:else]
        end

        answer
    end


    private

    def responds
        {
            question: 'Sure.',
             nothing: 'Fine. Be that way!',
                yell: 'Woah, chill out!',
                else: 'Whatever.'
        }
    end

    def teenager_answer(text)
        case text
            when question?(text)
                responds[:question]
            when nothing?(text)
                responds[:nothing]
            when yell?(text)
                responds[:yell]
            else
                responds[:else]
        end
    end

    def question?(text)
        text.end_with?('?')
    end

    def nothing?(text)
        text.match(/\A\s+\z/)
    end

    def yell?(text)
        text.match(/[:upper:]/) and not text.match(/[:lower:]/)
    end

    def behaves_like_string?(obj)
        obj = obj.to_str if obj.respond_to?(:to_str)
        obj.is_a?(String)
    end
end
