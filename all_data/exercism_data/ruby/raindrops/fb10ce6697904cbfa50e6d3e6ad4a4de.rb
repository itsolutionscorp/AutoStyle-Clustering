class Raindrops
  DICTIONARY = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

  def self.convert(num)
    res = ''
    DICTIONARY.each do |prime, word|
      res += word if (num%prime).zero?
    end
    res = num.to_s if res.empty?
    res
  end
end
