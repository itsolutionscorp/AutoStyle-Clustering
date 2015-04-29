class Raindrops
  def self.convert n
    gibber = [3, 5, 7].map.with_index do |divisor, i|
      ['Pling', 'Plang', 'Plong'][i] if n % divisor == 0
    end

    return n.to_s if gibber.compact.empty?
    gibber.join
  end
end
