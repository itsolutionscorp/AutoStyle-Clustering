require 'prime'

module Raindrops
    
    RAINDROP_SPEAK = {
        3 => 'Pling',
        5 => 'Plang',
        7 => 'Plong'
    }
    
    RAINDROP_SPEAK.default = nil
    
    def self.convert( drops )
        return drops.to_s if ( drops === 0 )
        
        # Prime.prime_division returns array of [ prime, times ]
        # This replaces each element with prime.
        factors = Prime.prime_division( drops ).map( &:first ).uniq
        
        # Convert to raindrop_speak and remove all nils
        convert = factors.map { |x| RAINDROP_SPEAK[ x ] }.compact
        
        convert.empty? ? drops.to_s : convert.join
    end
    
end
