class Raindrops

  def initialize
    @primes = [3, 5, 7]
    @words = ["Pling", "Plang", "Plong"]
  end

  def convert(x)
    str = @primes.each_with_index.with_object("") do |(p, i), str| 
      str << @words[i] if x.modulo(p).zero?
    end
    str = x.to_s if str.empty?
    str
  end

end
