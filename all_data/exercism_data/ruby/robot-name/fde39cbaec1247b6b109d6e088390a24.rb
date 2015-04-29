class Robot
    attr_reader :name
    @@prng = Random.new

    def initialize
        a = 'A'.bytes[0] # char 'A' == 65
        @name = ""
        (1..5).each do |i|
            @name += (i < 3 ? (@@prng.rand(0..25)+ a).chr : (@@prng.rand(10)).to_s)
        end
    end

    def reset
        initialize
    end
end
