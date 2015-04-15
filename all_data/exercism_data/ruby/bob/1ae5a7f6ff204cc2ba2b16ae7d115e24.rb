class Bob
    def hey(address)
        return "Fine. Be that way!" if address =~ /\A\s*\z/

        # account for terribly excited spelling
        address.gsub! /![1!]+$/, '!'

        return "Woah, chill out!" if address =~ /[A-Z]/ and address == address.upcase

        return "Sure." if address =~ /\?\s*\z/

        return "Whatever."
    end
end
