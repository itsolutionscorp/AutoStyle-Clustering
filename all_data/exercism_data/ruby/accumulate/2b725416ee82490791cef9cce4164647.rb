class Array
    def accumulate
        a = []
        self.each { |e| a.push(yield e) }
        a
    end
end
