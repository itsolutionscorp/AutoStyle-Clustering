class Integer
    def factor? n
        self % n == 0
    end
end

module Raindrops
    module_function

    Transalations = { "Pling" => 3, "Plang" => 5, "Plong" => 7 }
    def translate n
        Transalations.each_with_object "" do |pair, output|
            output << pair[0] if n.factor? pair[1]
        end
    end

    def convert n
        if (translation = translate n) == ""
            n.to_s # no word for it in raindrop-speak
        else
            translation
        end
    end
end
