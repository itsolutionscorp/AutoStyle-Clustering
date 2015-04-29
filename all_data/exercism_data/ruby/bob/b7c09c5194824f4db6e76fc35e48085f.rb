class Bob

    def hey(remark)
        if is_silence?(remark)
            'Fine. Be that way!'
        elsif is_yell?(remark)
            'Whoa, chill out!'
        elsif is_question?(remark) 
            'Sure.'    
        else 'Whatever.'
        end
    end

    private

    def is_question?(remark)
        remark.end_with? '?'
    end

    def is_yell?(remark)
        remark =~ /[A-Z]/ && remark == remark.upcase
    end

    def is_silence?(remark)
        remark.strip == ''
    end

end
