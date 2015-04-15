class Bob
    def hey(remark)
        def yelling?(msg)
            alpha = false
            msg.each_char do |char|
                if char =~ /[[:alpha:]]/
                    alpha = true
                end
            end
            msg.upcase == msg && alpha
        end

        def silence?(msg)
            msg.strip.length == 0
        end

        def question?(msg)
            msg.end_with? '?'
        end

        if silence?(remark)
            'Fine. Be that way!'
        elsif yelling?(remark)
            'Woah, chill out!'
        elsif question?(remark)
            'Sure.'
        else
            'Whatever.'
        end
    end
end
