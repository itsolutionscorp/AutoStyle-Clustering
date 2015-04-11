class Raindrops
  def self.convert(num)
    dividers = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}
    output = num.to_s
    dividers.each do |prime, string|
      if num % prime == 0
        output << dividers[prime]
        output.gsub!(num.to_s, '')
      end
    end
    output
  end
end
