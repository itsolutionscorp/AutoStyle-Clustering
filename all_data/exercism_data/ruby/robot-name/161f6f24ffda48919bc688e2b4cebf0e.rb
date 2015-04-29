
class Robot
    
    attr_reader :name
    
    @@my_robots = Array.new()
    @@rng = Random.new()
    
    def initialize()
        reset
    end
    
    def reset
        loop do
            @name = get_name
            if not @@my_robots.include?(@name)
                @@my_robots.push(@name)
                break
            end
        end
    end
    
    private

    def get_name
        sprintf("%c%c%03d", @@rng.rand(65..90).chr, @@rng.rand(65..90).chr, @@rng.rand(1..999))
    end
end
