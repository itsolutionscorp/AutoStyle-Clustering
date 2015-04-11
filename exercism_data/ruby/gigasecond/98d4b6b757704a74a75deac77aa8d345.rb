require 'date'
require 'time'

class Gigasecond

    GIGASECOND = 10**9

    def self.from(date)

        # Wow. I made this way more complicated than need be.
        # On to iteration #2. PS: I miss Smalltalk.
        # Ruby feels like it has a lot of voodoo going on.
        # And why can't I find to_date and others
        # in the documentation?

        # To allow accurate calculations of partial days
        # we will do all calculations as Time objects.

        (date.to_time + GIGASECOND).to_date
    end
end
