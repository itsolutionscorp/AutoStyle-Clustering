# year.rb
# jennyb exercism.io github.com/jbasalone

class Year

    def self.leap?(year)
        @theyear = year
        divbyfour? && !acentury? || bcentury?
    end

    def self.theyear
      @theyear
    end
    #private

    def self.acentury?
        theyear.modulo(100).zero?
    end

    def self.divbyfour?
        theyear.modulo(4).zero?
    end

    def self.bcentury?
        theyear.modulo(400).zero?
    end
end
#self.theyear % 400 == 0 or (self.theyear % 4 == 0 and self.theyear % 100 != 0)
