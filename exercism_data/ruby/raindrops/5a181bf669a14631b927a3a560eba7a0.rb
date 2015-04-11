class Raindrops
  def self.convert(num)
    dividers = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}
    content = ''
    dividers.each do |prime, string|
      if num % prime == 0
        content << dividers[prime]
      end
    end
    content.empty? ? num.to_s : content
  end
end
