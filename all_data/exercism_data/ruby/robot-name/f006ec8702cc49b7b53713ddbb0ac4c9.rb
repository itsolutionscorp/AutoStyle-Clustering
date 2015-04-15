class Robot
    def name
        @name ||= (2.elements_from(*'A'..'Z') + 3.elements_from(*'0'..'9')).join
    end

    def reset
        @name = nil
    end
end

class Fixnum
    def elements_from(*array)
        self.times.reduce([]) do |memo, i|
            memo.push array.sample
        end
    end
end
