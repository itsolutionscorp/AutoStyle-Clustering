class Raindrops

  def self.convert(raindrops)

    drops = [[3, 'Pling'],
             [5, 'Plang'], 
             [7, 'Plong']]

    output = ''

    drops.each do |factor, raindrop_text|
      if raindrops % factor == 0
        output << raindrop_text
      end
    end

    output.empty? ? raindrops.to_s : output

  end

end
