module Raindrops
    module_function

    def convert n
        output = {
            "Pling" => 3,
            "Plang" => 5,
            "Plong" => 7
        }.each_with_object("") { |pair, output|
            output << pair[0] if (n % pair[1]) == 0
        }

        (output == "") ? n.to_s : output
    end
end
