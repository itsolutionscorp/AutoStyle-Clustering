class Grains
    def initialize
        def self.square(square)
            counter = 1
            grains = 1
            while (counter < square)
                grains*=2
                counter+=1
            end
            return grains
        end
        def self.total
            total_grains = 0
            counter = 1
            while (counter <= 64)
                total_grains+=Grains.new.square(counter)
                counter+=1
            end
            return total_grains
        end
    end
end
