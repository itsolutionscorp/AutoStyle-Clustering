class Prime
  BOOST = 4

  def self.nth input
    raise ArgumentError if input < 1
    index = input-1
    prime = []
    prime[0] = 2
    return prime[index] if (index == 0)

    test_number = 3
    while prime.length <= index do
      test_flag = true

      prime.each do |divisor|
        if test_number % divisor == 0
          test_flag = false
          break
        end
        break if divisor*BOOST >= test_number # performance boost
      end

      prime << test_number if test_flag
      test_number +=2
    end
    prime[index]
  end

end
# Execution speeds:
# BOOST = 1 -> 5.2s        BOOST = 4 -> 1.6s
# BOOST = 2 -> 2.8s        BOOST = 5 -> fail
# BOOST = 3 -> 2.0s
