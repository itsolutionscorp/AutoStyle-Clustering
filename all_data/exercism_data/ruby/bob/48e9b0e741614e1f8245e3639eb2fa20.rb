class Bob
    def hey(greeting)
        if not greeting or greeting.strip == ""
            "Fine. Be that way!"
        else
            greeting = greeting.gsub(/\r/, " ").gsub(/\n/, " ")
            if greeting.match(/[a-zA-Z]/) and greeting.upcase == greeting
                "Woah, chill out!" 
            else
                if greeting.match(/^.+\?$/)
                    "Sure."
                else
                    "Whatever."
                end
            end
        end
    end
end
