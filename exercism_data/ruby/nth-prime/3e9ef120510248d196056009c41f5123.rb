require 'prime'

class Prime

    def self.nth(aNumber)
        # Lol, the other way is stupid. Why didn't I just do this?
        raise ArgumentError, 'No 0th prime' if aNumber == 0
        # Prime library has some strange voodoo going on.
        # Fortunately, voodoo is very convenient.
        Prime.take(aNumber).last
    end

end
