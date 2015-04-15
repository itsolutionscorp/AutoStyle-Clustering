class Bob
        def hey(message)
                lowercase_chars = message.scan /[a-z]/
                uppercase_chars = message.scan /[A-Z]/
                case
                when message.empty?
                        'Fine. Be that way.'
                when message.end_with?('?')
                        'Sure.'
                when uppercase_chars.count > lowercase_chars.count
                        'Woah, chill out!'
                else
                        'Whatever.'
                end
        end
end
