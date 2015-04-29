class Raindrops
  def self.convert(num)
    word_map = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }
    word = ""
    word_map.each { |k, v| word << v if num % k == 0 }
    word.empty? ? num.to_s : word
  end
end
