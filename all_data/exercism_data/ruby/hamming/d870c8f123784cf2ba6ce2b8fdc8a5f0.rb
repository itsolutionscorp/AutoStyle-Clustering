module Hamming
        
    ##
    # Computes the hamming distance between left and right using reject
    #
    def self.compute( left, right )
        left.chars.each_with_index.reject{ | char, i | right[ i ] === char || i >= right.length }.count
    end
    
    ##
    # Computes the hamming distance between left and right using inject / reduce
    #
    def self.compute_using_reduce( left, right )
        left.chars.each_with_index.inject( 0 ) do | distance, ( char, i ) |
            distance + ( right[ i ] === char || i >= right.length ? 0 : 1 )
        end
    end
    
end
