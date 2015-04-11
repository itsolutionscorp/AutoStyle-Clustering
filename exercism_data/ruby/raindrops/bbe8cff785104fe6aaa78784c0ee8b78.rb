class Raindrops
  LYRICS = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(n)
    song = [3, 5, 7].map { |d| n % d == 0 ? LYRICS[d] : '' }.join('')
    song.empty? ? n.to_s : song
  end
end
