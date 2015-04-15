class Raindrops
  def self.convert(n)
    str = responses.map do |mod, response|
      n % mod == 0 ? response : nil
    end.compact.join

    str.empty? ? n.to_s : str
  end

  def self.responses
    {
      3 => 'Pling',
      5 => 'Plang',
      7 => 'Plong'
    }
  end

end
