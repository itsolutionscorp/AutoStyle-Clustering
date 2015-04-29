# Tested with 1.9.3 and 2.0.0
# Bob responds to four cases
class Bob
    def hey (input_message)
        response_script = { 
            ->(prompt){ prompt.empty? } => 'Fine. Be that way!',
            ->(prompt){ prompt.upcase == prompt } => 'Woah, chill out!',
            ->(prompt){ prompt.end_with? '?' } => 'Sure.',
            ->(prompt){ true } => 'Whatever.'
        }
        response_script.each_key do |message| 
            return response_script[message] if message.call input_message
        end
    end
end
