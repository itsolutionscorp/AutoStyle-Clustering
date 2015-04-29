class Robot
    def initialize
        name #boot
    end
    
    def name
        @name ||= RobotNameGenerator.generate
    end
    
    def reset
        @name = nil #wipe
    end
end

module RobotNameGenerator
    @@used = []
    
    def self.generate
        loop do
            result = 
                ('a'..'z').to_a.shuffle[0,2].join.upcase + 
                ('0'..'9').to_a.shuffle[0,3].join
                
            return ( @@used << result ).last unless @@used.include? result
        end
    end
end
