class Fixnum
    SYMBOLS = { 
        1    => 'I', 
        5    => 'V', 
        10   => 'X', 
        50   => 'L', 
        100  => 'C', 
        500  => 'D', 
        1000 => 'M' 
    }
    SUBTRACTORS = [ 
        [1000, 100],
        [500, 100],
        [100, 10],
        [50, 10],
        [10, 1],
        [5, 1],
        [1, 0]
    ]
    def to_roman
        return SYMBOLS[self] if SYMBOLS.has_key?(self)
        SUBTRACTORS.each do |cut, subtractor| 
            return cut.to_roman + (self - cut).to_roman      if self >  cut
            return subtractor.to_roman + (self + subtractor).to_roman  if self >= cut - subtractor and self < cut
        end 
    end
end
