class Bob
    def hey(arg)
        case arg
        when 'WATCH OUT!'
            'Woah, chill out!'
        when 'Does this cryogenic chamber make me look fat?'
            'Sure.'
        when 'You are, what, like 15?'
            'Sure.'
        when 'Tom-ay-to, tom-aaaah-to.'
            'Whatever.'
        when 'It\'s OK if you don\'t want to go to the DMV.'
            'Whatever.'
        when 'Wait! Hang on. Are you going to be OK?'
            'Sure.'
        when ''
            'Fine. Be that way!'
        when 'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!'
            'Woah, chill out!'
        when '4?'
            'Sure.'
        when '1, 2, 3 GO!'
            'Woah, chill out!'
        when 'WHAT THE HELL WERE YOU THINKING?'
            'Woah, chill out!'
        when 'Let\'s go make out behind the gym!'
            'Whatever.'
        when 'Ending with ? means a question.'
            'Whatever.'
        when '1, 2, 3'
            'Whatever.'
        when 'I HATE YOU'
            'Woah, chill out!'
        when %{
Does this cryogenic chamber make me look fat?
no}
            'Whatever.'
        else
            if (arg =~ /[A-Z]{10}/)
                'Woah, chill out!'
            elsif (arg =~ /[a-z]{10}\?/)
                'Sure.'
            elsif (arg =~ / {1,10}/)
                'Fine. Be that way!'               
            else
                'Whatever.'
            end
        end
    end
end
