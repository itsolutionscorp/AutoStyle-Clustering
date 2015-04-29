class Raindrops
  def convert nr
    sounds = drops.select do |_, prime|
      nr % prime == 0
    end.keys

    sounds.empty? ? nr.to_s : sounds.join
  end

  def drops
    {
      Pling: 3,
      Plang: 5,
      Plong: 7
    }
  end
end
