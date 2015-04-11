class Raindrops

  def convert(number)
    plingplangplong = {
      3 => 'Pling', 
      5 => 'Plang',
      7 => 'Plong',
      }.map do |factor, noise|
        noise if number % factor == 0
      end
    plingplangplong.any? ? plingplangplong.join : number.to_s
  end

end
