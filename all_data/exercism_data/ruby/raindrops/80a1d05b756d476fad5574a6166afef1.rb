class Raindrops
  SOUNDS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(n)
    response = ''

    SOUNDS.each do |i, sound|
      response << sound if (n % i).zero?
    end

    response.empty? ? n.to_s : response
  end
end
