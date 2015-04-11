class Binary
    attr_reader :n

    def initialize n
      @n = n
    end

    def to_decimal
      if @n =~ /\b[01]+\b/
        binary = @n.scan(/\d/).map(&:to_i)
      else
        binary = [0]
      end

      sum = 0
      binary.reverse.each_with_index do | b, index |
        sum += (2**index) * b
      end
      sum
    end
  end
