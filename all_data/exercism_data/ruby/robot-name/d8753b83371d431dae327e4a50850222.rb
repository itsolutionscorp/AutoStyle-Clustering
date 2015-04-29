class Robot

    attr_reader :name

    def initialize
        @name = randomNameGen
    end

    def reset
        initialize
    end

    private
        def randomNameGen
            name = String.new
            2.times {name << (rand(25) + 65).chr}
            3.times {name << rand(10).to_s}
            name
        end
end
