# vim: set ts=4 sts=4 sw=4 et

class Bob
    def hey(greeting)
        greeting.strip!

        if greeting.empty?
            "Fine. Be that way!"
        elsif all_upcase(greeting) then
            "Woah, chill out!"
        elsif greeting.end_with? "?" then
            "Sure."
        else
            "Whatever."
        end
    end

    private

    def all_upcase(message)
	message.upcase == message
    end
end
