module Gigasecond
    
    def self.from( date_of_birth )
        ( date_of_birth.to_time + 10 ** 9 ).to_date
    end
    
end
