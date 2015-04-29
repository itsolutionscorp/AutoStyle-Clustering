class Raindrops
  TRANSLATION = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(num)
    s = TRANSLATION.select{|factor| num % factor == 0 }.values.join
    s.empty? ? num.to_s : s   
  end
end
