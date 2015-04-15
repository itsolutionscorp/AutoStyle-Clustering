class Raindrops
    def self.convert(num)
        forms = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }
       output(((1..num).select { |n| num%n == 0 }).uniq.reduce([]) { |sum, x| sum << forms[x]  }.compact, num)
    end
    def self.output(mas, num)
        if mas.empty?
            return num.to_s
        else
           return  mas.reduce("") {|sum, x| sum << x}
        end
    end
end
