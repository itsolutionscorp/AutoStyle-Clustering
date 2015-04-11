class Robot
    @@names = [] #TRACKS ALL NAMES EVER ASSIGNED TO THE CLASS
    def generate
        @name = (1..5).map { |x| rand(x<3 ? 65..90 : 48..57).chr }.join
    end
    def reset
        @name = generate()
        @name = generate() while @@names.include?(@name) #LOOP UNTIL NAME NOT FOUND
        @@names.push @name
        @name
    end
    def initialize
        @name = reset()
    end
    def name
        @name
    end
end
