class Robot
    LETTERS = ['a'..'z', 'A'..'Z'].reduce([]) {|b, r| b + r.to_a}
    NUMBERS = (1..9).to_a

    @@names_cache = []
    attr_accessor :name

    def initialize
        compute_name
    end

    def reset
        compute_name
    end

private
    
    def compute_name
        @name = LETTERS.sample(2).join + NUMBERS.sample(3).join

        if (@@names_cache.include?(@name))
            compute_name
        else
            @@names_cache << @name
        end

        @name
    end
end
