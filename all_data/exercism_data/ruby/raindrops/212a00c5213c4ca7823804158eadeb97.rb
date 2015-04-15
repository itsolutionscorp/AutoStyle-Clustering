class Raindrops

  WORDS = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

  def self.convert number
    primes   = []
    quocient = number

    while quocient > 1
      prime    = first_prime_or_number(quocient)
      primes  << prime
      quocient = quocient / prime
    end

    output = (primes & WORDS.keys).map { |n| WORDS[n] }.join
    output.empty? ? number.to_s : output
  end

  private

  def self.first_prime_or_number(number)
    2.upto(number-1) do |n|
      return n if number % n == 0
    end
    number
  end

end
