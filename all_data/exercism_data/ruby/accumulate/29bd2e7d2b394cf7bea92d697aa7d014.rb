class Array
    def accumulate
        copy = self.clone
        copy.length.times do |x|
            copy[ x ] = yield copy[ x ]
        end
        copy
    end
end
