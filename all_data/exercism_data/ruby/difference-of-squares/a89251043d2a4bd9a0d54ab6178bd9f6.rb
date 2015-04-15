class Squares
    def initialize(nums)
        @nums = nums
    end

    def square_of_sums
        sum = 0
        1.upto(@nums) { |num| sum += num }

        sum**2
    end

    def sum_of_squares
        sum = 0
        1.upto(@nums) { |num| sum += num**2 }

        sum
    end

    def difference
        square_of_sums - sum_of_squares
    end
end
