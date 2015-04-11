class Raindrops

  def self.convert(num)
    call = create_onomatopoeia(num)
    call.empty? ? num.to_s : call
  end

  def self.onomatopoeia(letter)
    onomatopoeia = ['Pl', 'ng']
    onomatopoeia.insert(1, letter)
    onomatopoeia.join
  end

  def self.create_onomatopoeia(num)
    modulo_map = {3 => 'i', 5 => 'a', 7 => 'o'}
    modulo_map.collect do |d, l|
      if num % d == 0 then onomatopoeia(l) end
    end.join
  end
end
