def compute shorter, longer
        if (shorter.length > longer.length)
            self.compute longer, shorter
        end

        str_to_char_array = Proc.new do |x|
            x.chars.to_a
        end

        (shorter, longer) = [shorter, longer].collect(&str_to_char_array)

        shorter.zip(longer).count do |current|
            current[0] != current[1]
        end
    end