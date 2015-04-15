class Raindrops
  def convert nr
    sounds = []
    sounds << "Pling" if (nr % 3).zero?
    sounds << "Plang" if (nr % 5).zero?
    sounds << "Plong" if (nr % 7).zero?

    sounds.empty? ? nr.to_s : sounds.join
  end
end
