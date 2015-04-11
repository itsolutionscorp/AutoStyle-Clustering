class Raindrops

  DROP_TALK = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(int)
    list = DROP_TALK.select {|prime| int % prime == 0 }.values.join
    
    list.empty? ? int.to_s : list
  end

end
