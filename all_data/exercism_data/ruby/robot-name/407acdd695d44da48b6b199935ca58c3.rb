class Robot

    attr_reader :name

    @@takenNames = []

    def initialize
        @name = randomNameGen
    end

    def reset
        # Do we want names to just be unique? Or never used again
        # until the end of time (or as long as this class is instanciated)?
        @@takenNames.delete(@name)
        initialize
    end

    private
        def randomNameGen
            name = String.new
            # Create a name from 2 cap letters and 3 numbers
            2.times {name << (rand(25) + 65).chr}
            3.times {name << rand(10).to_s}
            # Now it checks to ensure name hasn't already been taken.
            @@takenNames.include?(name) ? name = randomNameGen : @@takenNames.push(name)
            name
        end
end
