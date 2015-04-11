class Triplet
    attr_reader :sides

    def initialize(a, b, c)
        @sides = [a, b, c].sort
    end

    def sum()
        @sides.reduce(:+)
    end

    def product()
        @sides.reduce(:*)
    end

    def pythagorean?()
        @sides[0]**2 + @sides[1]**2 == @sides[2]**2
    end

    def self.where(min_factor: 1, max_factor: 10, sum: nil)
        [].tap do |triplets|
            (min_factor..max_factor).each do |a|
                (a..max_factor).each do |b|
					c = Math.sqrt(a**2 + b**2)
					break if c.to_i > max_factor
					if c % 1 == 0
						c = c.to_i
						next if sum && sum != a+b+c
						triplets << Triplet.new(a, b, c) 
					end
                end
            end
        end
    end
end
