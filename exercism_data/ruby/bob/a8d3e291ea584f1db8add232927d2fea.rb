class Bob

    QUESTION_RESPONSE   = 'Sure.'
    YELLING_RESPONSE    = 'Whoa, chill out!'
    SILENCE_RESPONSE    = 'Fine. Be that way!'
    OTHER_RESPONSE      = 'Whatever.'
    
    def hey( input )
        return SILENCE_RESPONSE if input.scan( /[0-9A-Za-z]/ ).empty?
        return YELLING_RESPONSE if !input.scan( /[A-Z]/ ).empty? && input.scan( /[a-z]/ ).empty?
        return QUESTION_RESPONSE if input.strip[ -1 ] == '?'
        OTHER_RESPONSE
    end
end
