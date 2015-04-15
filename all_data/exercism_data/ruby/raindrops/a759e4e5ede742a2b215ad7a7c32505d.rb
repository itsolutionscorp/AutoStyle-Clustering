class Raindrops

  def self.convert(n)
    str = [:pling, :plang, :plong].map{|test_name| self.send(test_name, n)}.compact.join
    return str if str.length > 0

    n.to_s
  end

  def self.pling(n)
    factor_words(3, "Pling", n)
  end

  def self.plang(n)
    factor_words(5, "Plang", n)
  end

  def self.plong(n)
    factor_words(7, "Plong", n)
  end

  def self.factor_words(factor, word, n)
    word if n % factor == 0
  end
end
