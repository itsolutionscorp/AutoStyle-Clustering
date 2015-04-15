class Raindrops
  def self.convert(value)
    primes = {3 => 'Pling', 5 =>  'Plang', 7 => 'Plong'}
    response = ''
    primes.keys.each {|i| response += primes[i] if (value%i).eql? 0}
    return response.empty? ? value.to_s : response
  end
end
