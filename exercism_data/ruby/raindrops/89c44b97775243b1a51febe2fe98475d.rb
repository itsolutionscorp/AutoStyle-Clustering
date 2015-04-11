class Raindrops
    @special_nums = {
        3 => 'Pling',
        5 => 'Plang',
        7 => 'Plong'
    }

    @factors = []

    def self.convert(number)
        @factors = []
        factors = self.get_next_factor(number)

        response = ''
        factors.uniq.each do |factor|
            if @special_nums.include?(factor)
                response << @special_nums[factor]
            end
        end

        return number.to_s if response == ''
        response
    end

    def self.get_next_factor(number)
        if self.is_prime?(number)
            @factors.push(number)
        else
            (2...number).each do |factor|
                if number % factor == 0
                    @factors.push(factor)
                    return self.get_next_factor(number / factor)
                end
            end
        end
    end

    def self.is_prime?(number)
        return false if number.even?

        (3...number).each do |factor|
            if number % factor == 0
                return false
            end
        end

        true
    end
end
