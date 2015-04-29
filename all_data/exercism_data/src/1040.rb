def compute( strandx, strandy )
        @string_position_counter = 0 #i'm not happy about this counter at all
        @hamming_distance = 0 #this is basically another counter -- i'm so bad at this
        @strandx = strandx
        @strandy = strandy
        @strandx.each_char do |char|
            if char != @strandy[@string_position_counter]
                #this is a quick check to see if the second string ends prematurely 
                if @strandy[@string_position_counter] == nil
                    return @hamming_distance
                end
                @hamming_distance += 1
            end
            @string_position_counter += 1 
        end
        return @hamming_distance
    end