class Robot
    def initialize
        letters = Array.new(2){rand(10..36).to_s(36)}.join.upcase
        numbers = Array.new(3){rand(0..9)}.join
        @name = letters+numbers
    end

    def name
        @name
    end

    def reset
        initialize
    end
end

#2 letters, 3 numbers
