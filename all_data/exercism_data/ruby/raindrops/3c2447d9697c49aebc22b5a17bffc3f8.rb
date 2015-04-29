class Raindrops

  def convert(n)
    sounds = []
    sounds << "Pling" if n % 3 == 0
    sounds << "Plang" if n % 5 == 0
    sounds << "Plong" if n % 7 == 0
    sounds.empty? ? n.to_s : sounds.join
  end

end
