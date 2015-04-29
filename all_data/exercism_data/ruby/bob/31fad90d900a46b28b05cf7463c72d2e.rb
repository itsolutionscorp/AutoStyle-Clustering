class Bob
    @@has_letters = /[a-zA-Z]/

    def hey(greeting)
        if not greeting or greeting.strip.empty?
            "Fine. Be that way!"
        else
            greeting = greeting.gsub(/\r/, " ").gsub(/\n/, " ")
            if greeting.match(@@has_letters) and greeting.upcase == greeting
                "Woah, chill out!" 
            else
                if greeting.end_with?('?')
                    "Sure."
                else
                    "Whatever."
                end
            end
        end
    end
end
