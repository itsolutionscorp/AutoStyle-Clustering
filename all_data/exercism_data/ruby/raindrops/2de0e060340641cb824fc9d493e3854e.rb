module Raindrops
    RULES = [
        [->(x) { (x % 3).zero? }, "Pling"],
        [->(x) { (x % 5).zero? }, "Plang"],
        [->(x) { (x % 7).zero? }, "Plong"]
    ]

    def Raindrops.convert number
        if RULES.any? { |test, _| test.call number }
            RULES.reduce("") do |acc, (test, output)|
                (test.call number) ? acc + output : acc
            end
        else
            number.to_s
        end
    end
end
