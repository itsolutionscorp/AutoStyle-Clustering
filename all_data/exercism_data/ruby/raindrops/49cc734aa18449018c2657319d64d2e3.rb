class Raindrops
  NOISES = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert( value )
    str = NOISES.reduce( '' ) { |a, (k, v)| value % k == 0 ? a + v : a }

    str.empty? ? value.to_s : str
  end
end
