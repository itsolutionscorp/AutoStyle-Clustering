class Raindrops

    def self.convert(aNumber)
        # Manually deriving the factors was way too computationally
        # expensive and a lot of unneeeded work.
        # Since I only need to check for 3, 5, and 7, any other
        # prime numbers are useless to have. Modulo serves
        # the needs of this program quite well.

        returnString = ''
        returnString += "Pling" if aNumber % 3 == 0
        returnString += "Plang" if aNumber % 5 == 0
        returnString += "Plong" if aNumber % 7 == 0
        # This looks a bit neater way to tie it up than
        # the last implementation.
        returnString.empty? ? aNumber.to_s : returnString
    end
end
