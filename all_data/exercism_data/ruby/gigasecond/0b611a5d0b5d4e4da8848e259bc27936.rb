require 'date'
require 'time'

class Gigasecond
    def self.from(birthDate)
        gigaseconds = 10**9

        # To allow accurate calculations of partial days
        # we will do all calculations as Time objects.
        if birthDate.is_a?(Date)
            birthDate = birthDate.to_time
        end

        timeOfGsAnniversary = birthDate + gigaseconds

        gsAnniversary = Date.parse(timeOfGsAnniversary.to_s)

        # Apparently unnecessary but it makes the code clear to me.
        return gsAnniversary
    end
end
