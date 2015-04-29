class Raindrops
 def self.convert(num)
    a = []
    a << "Pling" if num % 3 == 0
    a << "Plang" if num % 5 == 0
    a << "Plong" if num % 7 == 0
    a << num.to_s if a.empty?
    a.join
 end
end
