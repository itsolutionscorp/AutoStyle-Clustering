class Raindrops
  def self.convert(number)
    answer=(primes(number)).compact
    answer.empty? ? number.to_s : answer.join
  end

  def self.primes(number)
    answer=[]
    primes = { 3=>'Pling', 5=>'Plang', 7=>'Plong' }
    primes.each { |k,v|
      answer<<v if (number%k)==0
    }
    return answer
  end
end
