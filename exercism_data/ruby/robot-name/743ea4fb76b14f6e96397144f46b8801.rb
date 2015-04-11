class Robot
    @@names = [] #TRACKS ALL NAMES EVER ASSIGNED TO THE CLASS
    attr_reader:name
    def generate
        @name = (1..5).map { |x| rand(x<3 ? 65..90 : 48..57).chr }.join
    end
    def reset
        @name = generate() while @name==nil || @@names.include?(@name) #LOOP UNTIL NAME EXISTS AND NOT FOUND
        @@names.push @name
        @name
    end
    def initialize
        @name = reset()
    end
end
