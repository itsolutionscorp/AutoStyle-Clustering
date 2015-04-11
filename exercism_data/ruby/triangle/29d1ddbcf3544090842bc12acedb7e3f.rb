class TriangleError < RuntimeError
end

class Triangle
    def initialize(a, b, c)
        @a = a
        @b = b
        @c = c
        raise TriangleError if illegal?
    end

    def kind
        return :equilateral if all_sides_equal?
        return :isosceles if two_sides_equal?
        :scalene
    end

    private

    def all_sides_equal?
        (@a == @b) && (@b == @c)
    end

    def two_sides_equal?
        (@a == @b) || (@a == @c) || (@b == @c)
    end

    def illegal?
        non_positive_side? || one_side_too_long?
    end

    def non_positive_side?
        (@a <= 0) || (@b <= 0) || (@c <= 0)
    end

    def one_side_too_long?
        (@a >= @b + @c) || (@b >= @a + @c) || (@c >= @a + @b)
    end
end
