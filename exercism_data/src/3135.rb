def compute shorter, longer
        if (shorter.length > longer.length)
            return self.compute longer, shorter
        end

        shorter, longer = [shorter, longer].collect(&:chars)

        shorter.zip(longer).count do |char1, char2|
            char1 != char2
        end
    end