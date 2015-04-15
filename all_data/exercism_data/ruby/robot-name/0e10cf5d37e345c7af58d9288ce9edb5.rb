class Robot
    
    LETTERS = ('A'..'Z').to_a
    attr_reader :name 

    def initialize
        @name = new_name
    end

    def reset
        initialize
    end
    
    private

    def new_name
        "#{LETTERS.sample(2).join('')}#{rand(100..999)}"
    end

end
