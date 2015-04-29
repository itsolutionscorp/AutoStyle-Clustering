class Array
    def keep
        self.reduce([]) do |filtered, item|
            filtered.push item if yield item
            filtered
        end
    end

    def discard
        self.reduce([]) do |filtered, item|
            filtered.push item unless yield item
            filtered
        end
    end
end
