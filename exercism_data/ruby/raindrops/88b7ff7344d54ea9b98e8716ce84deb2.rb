class Raindrops
  DROPS = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

  def convert(n)
    output = DROPS.keys.map do |factor|
      DROPS[factor] if n % factor == 0
    end.join('')
    output.empty? ? n.to_s : output
  end
end
