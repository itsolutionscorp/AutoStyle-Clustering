class Raindrops

  def self.convert(num)
    vals = get_primes(num)
    mappings = {
      "3" => "Pling",
      "5" => "Plang",
      "7" => "Plong"
    }
    return "#{num}" if vals.empty?
    vals.each_with_object("") { |i, a| a << mappings[i.to_s] }
  end


  private

  def self.get_primes(num)
    primes = [3,5,7]
    primes.each_with_object([]) { |i, a| a << i if num % i == 0 }
  end

end
