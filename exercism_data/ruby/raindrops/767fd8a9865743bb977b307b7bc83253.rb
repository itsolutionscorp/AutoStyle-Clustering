class Raindrops

  class << self

    def convert number
      sounds = {3=>'Pling', 5=>'Plang', 7=>'Plong'}
      responses = []
      factors = []

      factors = factorize(number) if number > 1
      
      sounds.each do |key, val|
        responses << val if factors.include? key 
      end

      speak = if responses.empty?
        number.to_s
      else
        responses.join  
      end
      
      speak
    end

    private
    
    def primes
      [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113]
    end
    
    def factorize number
      results = []
      factors = []

      results << number if results.empty?

      until primes.include? results.last
        factors << lowest_prime_factor(results.last)
        results << results.last / factors.last        
      end

      factors << results.last

      factors
    end

    def lowest_prime_factor number
      results = primes.inject([]) do |results, digit|
        results << digit if number.%(digit).zero?
        results
      end

      results.min
    end

  end

end
