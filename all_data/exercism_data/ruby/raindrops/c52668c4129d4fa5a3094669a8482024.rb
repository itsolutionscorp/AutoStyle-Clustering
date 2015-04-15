class Raindrops

  PRIME_FACTS = { 
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }

  def self.convert(num)
    str = ""
    PRIME_FACTS.keys.each do |prime_fact|
      if num%prime_fact == 0
        str += PRIME_FACTS[prime_fact]
      end
    end
    return str.empty? ? "#{num}" : str
  end
end
