class Raindrops

  def convert(i)
    plingplangplong = 
    {
      3 => 'Pling', 
      5 => 'Plang',
      7 => 'Plong',
      }.map do |factor, sound|
        sound if i % factor == 0
      end
    plingplangplong.any? ? plingplangplong.join : i.to_s
  end
end
