class School
    def initialize
        @archive = {}
    end
    
    def add( name, grade )
        @archive[ grade ] ||= []
        @archive[ grade ].push( name ).sort!
    end
    
    def grade( grade )
        @archive[ grade ] || []
    end
    
    def to_hash
        Hash[ @archive.sort_by{ |k,v| k } ]
    end
end
