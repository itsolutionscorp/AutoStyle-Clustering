class Bob
    def hey(mssg)
        if (isShouting mssg)
            'Woah, chill out!'
        elsif (isQuestion mssg)
            'Sure.'
        elsif (isSilence mssg)
            'Fine. Be that way!'
        else
            'Whatever.'
        end
    end

private
    def isLower(c)
        c >= 'a' && c <= 'z'
    end

    def isUpper(c)
        c >= 'A' && c <= 'Z'
    end

    def isShouting mssg
        anyUpper = false
        mssg.each_char do |c|
            if isLower(c)
                return false
            elsif isUpper(c)
                anyUpper = true
            end
        end
        anyUpper
    end

    def getLastChar str
        str[str.length - 1]
    end

    def isQuestion mssg
        getLastChar(mssg) == '?'
    end

    def isSilence mssg
        mssg.strip.empty?
    end
end
