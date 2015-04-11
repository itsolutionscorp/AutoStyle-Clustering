module Year
    def self.leap? year
        return true if every_four_centuries? year
        return false if century? year
        return true if every_four_years? year
    end

    def self.every_four_years? year
        year % 4 == 0
    end

    def self.century? year
        year % 100 == 0
    end

    def self.every_four_centuries? year
        year % 400 == 0
    end
end
