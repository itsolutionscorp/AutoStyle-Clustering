class Raindrops
  def self.convert(count)
    drops = ''
    if count % 3 == 0
      drops << 'Pling'
    end

    if count % 5 == 0
      drops << 'Plang'
    end

    if count % 7 == 0
      drops << 'Plong'
    end

    if drops == ''
      drops = count.to_s
    end

    drops
  end
end
