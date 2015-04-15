def compute( strandx, strandy )
        @string_position_counter = 0
        @hamming_distance = 0
        @strandx = strandx
        @strandy = strandy
        @strandx.each_char do |char|
            if char != @strandy[@string_position_counter]

                if @strandy[@string_position_counter] == nil
                    return @hamming_distance
                end
                @hamming_distance += 1
            end
            @string_position_counter += 1
        end
        return @hamming_distance
    end