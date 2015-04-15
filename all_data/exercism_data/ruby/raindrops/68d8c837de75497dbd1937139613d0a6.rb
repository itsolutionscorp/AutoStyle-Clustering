class Raindrops

  def self.convert(num)
    results = "" 
    sounds = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}
    
    sounds.each do |k, v|
      results << v if num % k == 0
    end

    results.empty? ? num.to_s : results
  end

end
