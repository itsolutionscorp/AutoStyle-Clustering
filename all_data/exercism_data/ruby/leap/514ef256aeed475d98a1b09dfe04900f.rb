module Year
    def self.leap? year
        (every_four_years? year) &&
            (!(century? year) || (every_four_centuries? year))
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
