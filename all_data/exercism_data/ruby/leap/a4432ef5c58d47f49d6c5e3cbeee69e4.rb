module Year
    def self.leap?( n )
        return false if ( n % 4 ) != 0
        return true if ( n % 100 ) != 0
        return ( n % 400 ) == 0
    end
end
