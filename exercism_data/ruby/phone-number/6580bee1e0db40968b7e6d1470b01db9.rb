class PhoneNumber

    attr_reader :number

    def initialize(n)
        n = n.tr('-. ()', '')
        case
        when n.length < 10
            @number = "0000000000"
        when n.length == 10
            @number = n
        when n.length == 11
            if n[0] != "1"
                @number = "0000000000"
            else
                @number = n[1..11]
            end
        else
            @number = "0000000000"
        end
    end

    def area_code
        @number[0..2]
    end

    def to_s
        "(#{@number[0..2]}) #{@number[3..5]}-#{@number[6..9]}"
    end

end
